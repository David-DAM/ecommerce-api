package com.david.ecommerceapi.product.service;

import com.david.ecommerceapi.exception.domain.NotFoundException;
import com.david.ecommerceapi.product.repository.ProductRepository;
import com.david.ecommerceapi.product.domain.Product;
import com.david.ecommerceapi.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

        String filecode = this.fileUploadUtil.saveFile(fileName, multipartFile);

        product.setImage(filecode+"-"+multipartFile.getOriginalFilename());

        return this.productRepository.save(product);
    }

    public List<Product> findAll(){//Integer page, Integer pageSize, String sortBy

//        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
//
//        Page<Product> pagedResult = this.productRepository.findAll(paging);
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Product>();
//        }

        return this.productRepository.findAll();
    }

    public Optional<Product> findById(Long id){

        Optional<Product> product =  this.productRepository.findById(id);

        if(product.isEmpty()) throw new NotFoundException("Producto con Id:"+id+" no encontrado");

        return Optional.of(product.get());
    }

    public Product update(Product product, Optional<MultipartFile> multipartFile) throws IOException {

        Optional<Product> productDb = this.productRepository.findById(product.getId());

        if(productDb.isEmpty()) throw new NotFoundException("El producto no fue encontrado");

        BeanUtils.copyProperties(product,productDb.get(),"image");

        if (multipartFile.isPresent()){

            String fileName = StringUtils.cleanPath(multipartFile.get().getOriginalFilename());

            String filecode = this.fileUploadUtil.saveFile(fileName, multipartFile.get());

            productDb.get().setImage(filecode+"-"+multipartFile.get().getOriginalFilename());

        }

        this.productRepository.save(productDb.get());

        return productDb.get();
    }

    public Product delete(Long id){
        Product product = this.productRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("El producto no fue encontrado") );

        this.productRepository.delete(product);

        return product;
    }

}
