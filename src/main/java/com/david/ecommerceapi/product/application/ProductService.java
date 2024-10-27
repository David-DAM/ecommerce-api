package com.david.ecommerceapi.product.application;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.product.domain.ProductRepository;
import com.david.ecommerceapi.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FileUploadUtil fileUploadUtil;

    //TODO Replace Optional with .orElseThrow
    public Product save(Product product, MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String filecode = fileUploadUtil.saveFile(fileName, multipartFile);

        product.setImage(filecode + "-" + multipartFile.getOriginalFilename());

        return productRepository.save(product);
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

        return productRepository.findAll();
    }

    public Product findById(Long id) {

        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with Id:" + id + " not found"));
    }

    public Product update(Product product, Optional<MultipartFile> multipartFile) throws IOException {

        Optional<Product> productDb = productRepository.findById(product.getId());

        if (productDb.isEmpty()) throw new NotFoundException("El producto no fue encontrado");

        BeanUtils.copyProperties(product, productDb.get(), "image");

        if (multipartFile.isPresent() && multipartFile.get().getOriginalFilename() != null) {

            String fileName = StringUtils.cleanPath(multipartFile.get().getOriginalFilename());

            String filecode = fileUploadUtil.saveFile(fileName, multipartFile.get());

            productDb.get().setImage(filecode + "-" + multipartFile.get().getOriginalFilename());

        }

        productRepository.save(productDb.get());

        return productDb.get();
    }

    public Product delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El producto no fue encontrado"));

        productRepository.deleteById(product.getId());

        return product;
    }

}
