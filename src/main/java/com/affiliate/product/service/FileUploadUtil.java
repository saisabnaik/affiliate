package com.affiliate.product.service;

import java.io.*;
import java.nio.file.*;
 
import org.springframework.web.multipart.MultipartFile;
 
public class FileUploadUtil {
     
    public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        System.out.println(uploadPath); 
        
        if (!Files.exists(uploadPath)) {
        	System.out.println("file path created"+uploadPath);
            Files.createDirectories(uploadPath);
            System.out.println("directory created");
        }else {
        	System.out.println("directory already exists..");
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println("file path submitted"+uploadPath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }
}