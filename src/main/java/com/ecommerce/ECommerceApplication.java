package com.ecommerce;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerce.services.util.CloudinaryConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
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
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.ecommerce"))
				.build()
				.apiInfo(apiDetails());
	}
	private ApiInfo apiDetails(){
		return new ApiInfo(
				"E commerce API",
				"Set of Apis for the E commerce API",
				"1.0",
				"For E-commerce only",
				new Contact("Tee","e-commercebytee","onabanjotitus01@gmail.com"),
				"E commerce License",
				"EcommerceBytee",
				Collections.emptyList()



		);

	}

}
