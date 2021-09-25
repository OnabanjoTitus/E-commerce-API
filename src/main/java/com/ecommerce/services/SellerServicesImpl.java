package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.ProductRepository;
import com.ecommerce.data.repository.SellerRepository;
import com.ecommerce.services.cloud.CloudStorageService;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SellerServicesImpl  implements SellerServices{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CloudStorageService cloudStorageService;

    @Override
    public CustomerRequestDto addAccount(CustomerRequestDto customerRequestDto) throws AccountCreationException {
        log.info("The Customer Information received is -->{}", customerRequestDto);
        if(customerRequestDto.getEmailAddress().isBlank()){
            throw new AccountCreationException("Customer email address cannot be blank, please enter a valid email address ");
        }
        if(customerRequestDto.getPassword().isBlank()){
            throw new AccountCreationException("Customer password cannot be blank, please enter a valid password");
        }
        if(!customerRequestDto.getPassword().equals(customerRequestDto.getConfirmPassword())){
            throw new AccountCreationException("The Passwords do not match please enter matching passwords");
        }
        if(customerRequestDto.getRole().equals(Role.BUYER)|| Objects.requireNonNull(customerRequestDto.getRole().toString()).isBlank()){
            throw new AccountCreationException("This role is invalid for a seller");
        }
        Seller seller= new Seller();
        modelMapper.map(customerRequestDto,seller);
        log.info("The seller before saving is -->{}",seller);
        sellerRepository.save(seller);
        return null;
    }

    @Override
    public List<SellerDto> findSellerByName(String sellerName) throws AccountException {
        if(sellerName.isBlank()){
            throw new AccountException("Seller Name cannot be blank");
        }
        List<SellerDto> sellerDtoList = sellerRepository.findSellerBySellerName(sellerName)
                .stream().map(seller ->modelMapper.map(seller, SellerDto.class)).collect(Collectors.toList());
        if(sellerDtoList.isEmpty()){
            sellerDtoList=sellerRepository.findSellerBySellerNameContaining(sellerName)
                    .stream().map(seller -> modelMapper.map(seller,SellerDto.class))
                    .collect(Collectors.toList());
            if(sellerDtoList.isEmpty()){
                throw new AccountException("We do not have seller with the name "+sellerName+" in our records");
            }
        }
        return sellerDtoList;
    }

    @Override
    public List<ProductRequest> findProductsByName(String productName) throws ProductException {
        if(productName.isBlank()){
            throw new ProductException("product Name cannot be blank");
        }
        List<ProductRequest> productRequestList=productRepository.findAllByProductNameContains(productName)
                .stream().map(product -> modelMapper.map(product,ProductRequest.class)).collect(Collectors.toList());
        if(productRequestList.isEmpty()){
            throw new ProductException("We do not have product with the name "+productName+" in our records");
        }
        return productRequestList;
    }

    @Override
    public List<ProductRequest> findProductsBySellerName(String sellerName) throws ProductException, AccountException {
        if(sellerName.isBlank()){
            throw new AccountException("product Name cannot be blank");
        }
        List<ProductRequest> productRequestList=productRepository.findAllBySellerSellerNameContains(sellerName)
                .stream().map(product -> modelMapper.map(product,ProductRequest.class)).collect(Collectors.toList());
        if(productRequestList.isEmpty()){
            throw new ProductException("We do not have seller with the name "+sellerName+" in our records");
        }
        return productRequestList;
    }

    @Override
    public Product sellerUploadsProduct(String loginToken, ProductDto productDto) throws AccountException, ProductException {
        if(loginToken.isBlank()){
            throw new AccountException("Login Token cannot be empty");
        }
        if(productDto.getProductName().isBlank()){
            throw new ProductException("product name cannot be empty");
        }
        if(productDto.getProductCategory().isBlank()){
            throw new ProductException("product category cannot be empty");
        }
        if(productDto.getProductPrice().isNaN()){
            throw new ProductException("product price cannot be empty");
        }
        if(productDto.getProductDescription().isBlank()){
            throw new ProductException("product description cannot be empty");
        }
        if(productDto.getProductImage().isEmpty()){
            throw new ProductException("Product image cannot be empty");
        }
        //Todo : find seller by token and upload a product
        Product product= new Product();
        if(productDto.getProductImage()!=null && !productDto.getProductImage().isEmpty()){
            Map<Object,Object> params=new HashMap<>();
            params.put("public_id","e-commerce/"+extractFileName(productDto.getProductImage().getName()));
            params.put("overwrite",true);
            log.info("Image parameters-->{}",params);
            try{
                Map<?,?> uploadResult = cloudStorageService.uploadImage(productDto.getProductImage(),params);
                product.setProductImage(String.valueOf(uploadResult.get("url")));
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        return null;
    }
    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }


}
