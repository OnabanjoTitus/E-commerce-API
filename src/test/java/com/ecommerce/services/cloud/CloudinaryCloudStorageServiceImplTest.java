package com.ecommerce.services.cloud;

import com.cloudinary.utils.ObjectUtils;
import com.ecommerce.data.models.Product;
import com.ecommerce.data.models.Role;
import com.ecommerce.data.models.Seller;
import com.ecommerce.dtos.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CloudinaryCloudStorageServiceImplTest {
    @Autowired
    CloudStorageService cloudStorageServiceImpl;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testImage(){
        File file= new File("C:\\Users\\DELL\\Desktop\\937259.jpg");
        assertThat(file.exists()).isTrue();
        Map<Object,Object> params= new HashMap<>();
        params.put("file",file);
        try {
            cloudStorageServiceImpl.uploadImage(file,params);
        }catch (IOException e){
            log.info("Error occurred -->{}",e.getMessage());
        }
    }
    @Test
    void uploadMultipartImageTest() throws IOException {
        Path path= Paths.get("C:\\Users\\DELL\\Desktop\\937259.jpg");
        MultipartFile multipartFile= new MockMultipartFile("937259.jpg","937259.jpg", "img/jpg", Files.readAllBytes(path));
        log.info("Multipart object created --> {}",multipartFile);
        assertThat(multipartFile).isNotNull();
        ProductDto productDto= new ProductDto();
        productDto.setProductImage(multipartFile);

        log.info("File name -->{}",productDto.getProductImage().getOriginalFilename());

        cloudStorageServiceImpl.uploadImage(multipartFile, ObjectUtils.asMap("public_id","test-Image/"+extractFileName(Objects.requireNonNull
                (productDto.getProductImage().getOriginalFilename()))));
        assertThat(productDto.getProductImage().getOriginalFilename()).isEqualTo("937259.jpg");
    }

    private String extractFileName(String fileName) {
        return fileName.split("\\.")[0];
    }

}