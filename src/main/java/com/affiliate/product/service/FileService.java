package com.affiliate.product.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.product.Product;
import com.affiliate.product.ProductImage;

@Service
public class FileService {

	
    public boolean uploadFile(MultipartFile file,int venderid,Product product)throws Exception 
    {
    	//String filePath = new ClassPathResource("").getFile().getAbsolutePath();
    	//String uploadDir = filePath + File.separator + "static" + File.separator + venderid + File.separator +product.getCategory().getCtitle() + File.separator;
    	//System.out.println("filePath "+filePath);
    	
    	String uploadDir = "productImages"+ File.separator +venderid + File.separator +product.getCategory() +File.separator;
    	Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
        	System.out.println("file path created"+uploadDir);
            Files.createDirectories(uploadPath);
            System.out.println("directory created");
        }else {
        	System.out.println("directory already exists..");
        }

        try {
        	
            Path copyLocation = Paths.get(uploadDir + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(),copyLocation, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
          return false;
        }
    }
}