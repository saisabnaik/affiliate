package com.affiliate.product.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.product.Category;
import com.affiliate.product.Product;
import com.affiliate.product.repository.ProductRepository;
import com.affiliate.product.service.FileUploadUtil;
import com.affiliate.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public boolean addProduct(Product product, Category category, MultipartFile multipartFile, int venderid) {

		try {

			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			product.setPname(product.getPname());
			product.setPdesc(product.getPdesc());
			product.setPdiscount(product.getPprice());
			product.setPprice(product.getPdiscount());
			product.setPprice(product.getPquantity());
			product.setPpic(fileName);
			product.setCategory(category);
			String uploadDir = "productImages/"+venderid + "/" ;
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			Product savedProduct = productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
