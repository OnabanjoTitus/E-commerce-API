package com.ecommerce;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerce.services.util.CloudinaryConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommerceApplication {
	@Autowired
	CloudinaryConfig cloudinaryConfig;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	public Cloudinary getCloudinary(){
		return new Cloudinary(ObjectUtils.asMap("cloud_name",cloudinaryConfig.getCloudName(),
				"api_key",cloudinaryConfig.getApikey(),"api_secret",cloudinaryConfig.getSecretKey()));
	}
}
