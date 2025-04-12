package com.david.ecommerceapi.product.application;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.infrastructure.repository.implementation.PostgresProductRepositoryImpl;
import com.david.ecommerceapi.product.infrastructure.repository.implementation.RedisProductRepositoryImpl;
import com.david.ecommerceapi.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private final PostgresProductRepositoryImpl postgresProductRepository;
    private final RedisProductRepositoryImpl redisProductRepository;
    private final FileUploadUtil fileUploadUtil;
    private final VectorStore vectorStore;

    public Product save(Product product, MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            String fileCode = fileUploadUtil.saveFile(fileName, file);
            product.setImage(fileCode + "-" + file.getOriginalFilename());
        }

        Product saved = postgresProductRepository.save(product);
        redisProductRepository.save(saved);
        saveVectorProduct(saved);

        return saved;
    }

    private void saveVectorProduct(Product product) {
        Document document = Document.builder()
                .id(UUID.randomUUID().toString())
                .text(product.getDescription())
                .metadata(Map.of(
                        "productId", product.getId().toString(),
                        "name", product.getName(),
                        "category", product.getCategory().name(),
                        "price", product.getPrice()
                ))
                .build();
        vectorStore.add(List.of(document));
    }

    public List<Product> findAll() {//Integer page, Integer pageSize, String sortBy

//     Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));

//        Page<Product> pagedResult = productRepository.findAll(paging);
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Product>();
//        }
        List<Product> products = redisProductRepository.findAll();

        if (!products.isEmpty()) {
            log.info("Found {} products in redis", products.size());
            return products;
        }

        products = postgresProductRepository.findAll();

        if (!products.isEmpty()) {
            redisProductRepository.saveAll(products);
        }
        log.info("Found {} products in database", products.size());

        return products;
    }

    public Product findById(Long id) {
        Optional<Product> optionalProduct = redisProductRepository.findById(id);

        if (optionalProduct.isPresent()) {
            log.info("Found product in redis with id {}", id);
            return optionalProduct.get();
        }

        Product product = postgresProductRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with Id:" + id + " not found"));

        redisProductRepository.save(product);
        log.info("Found product in database with id {}", id);

        return product;
    }

    public Product update(Product product, Optional<MultipartFile> multipartFile) throws IOException {

        Product productDb = postgresProductRepository.findById(product.getId()).orElseThrow(() -> new NotFoundException("El producto no fue encontrado"));

        BeanUtils.copyProperties(product, productDb, "image");

        if (multipartFile.isPresent() && multipartFile.get().getOriginalFilename() != null) {

            String fileName = StringUtils.cleanPath(multipartFile.get().getOriginalFilename());

            String filecode = fileUploadUtil.saveFile(fileName, multipartFile.get());

            productDb.setImage(filecode + "-" + multipartFile.get().getOriginalFilename());

        }

        postgresProductRepository.save(productDb);
        redisProductRepository.save(productDb);

        return productDb;
    }

    public Product delete(Long id) {
        Product product = postgresProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El producto no fue encontrado"));

        postgresProductRepository.deleteById(product.getId());
        redisProductRepository.deleteById(product.getId());
        vectorStore.delete(List.of(product.getId().toString()));

        return product;
    }

}
