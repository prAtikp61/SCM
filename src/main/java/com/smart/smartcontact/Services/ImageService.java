package com.smart.smartcontact.Services;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
String uploadImage(MultipartFile file, String filename);
String getUrlFromPublicId(String publicId);
}
