package com.david.ecommerceapi.product.application;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.infrastructure.repository.implementation.MySqlProductRepositoryImpl;
import com.david.ecommerceapi.product.infrastructure.repository.implementation.RedisProductRepositoryImpl;
import com.david.ecommerceapi.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final MySqlProductRepositoryImpl mySqlProductRepository;
    private final RedisProductRepositoryImpl redisProductRepository;
    private final FileUploadUtil fileUploadUtil;

    public Product save(Product product, MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String filecode = fileUploadUtil.saveFile(fileName, multipartFile);

        product.setImage(filecode + "-" + multipartFile.getOriginalFilename());
        Product saved = mySqlProductRepository.save(product);
        redisProductRepository.save(saved);
        return saved;
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

        products = mySqlProductRepository.findAll();

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

        Product product = mySqlProductRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with Id:" + id + " not found"));

        redisProductRepository.save(product);
        log.info("Found product in database with id {}", id);

        return product;
    }

    public Product update(Product product, Optional<MultipartFile> multipartFile) throws IOException {

        Product productDb = mySqlProductRepository.findById(product.getId()).orElseThrow(() -> new NotFoundException("El producto no fue encontrado"));

        BeanUtils.copyProperties(product, productDb, "image");

        if (multipartFile.isPresent() && multipartFile.get().getOriginalFilename() != null) {

            String fileName = StringUtils.cleanPath(multipartFile.get().getOriginalFilename());

            String filecode = fileUploadUtil.saveFile(fileName, multipartFile.get());

            productDb.setImage(filecode + "-" + multipartFile.get().getOriginalFilename());

        }

        mySqlProductRepository.save(productDb);
        redisProductRepository.save(productDb);

        return productDb;
    }

    public Product delete(Long id) {
        Product product = mySqlProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El producto no fue encontrado"));

        mySqlProductRepository.deleteById(product.getId());
        redisProductRepository.deleteById(product.getId());

        return product;
    }

}
