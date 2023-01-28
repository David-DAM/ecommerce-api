package com.david.ecommerceapi.product;

import com.david.ecommerceapi.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product, MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

        product.setImage(filecode+"-"+multipartFile.getOriginalFilename());

        return this.productRepository.save(product);
    }

}
