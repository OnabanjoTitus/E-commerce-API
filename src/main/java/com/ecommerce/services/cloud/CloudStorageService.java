package com.ecommerce.services.cloud;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudStorageService {

 Map<?,?> uploadImage(MultipartFile multipartFile,Map<?, ?> imageProperties) throws IOException;
}
