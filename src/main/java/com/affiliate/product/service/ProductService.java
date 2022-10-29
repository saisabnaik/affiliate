package com.affiliate.product.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.affiliate.product.Category;
import com.affiliate.product.Product;
import com.affiliate.product.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;



	private final Logger log = LoggerFactory.getLogger(this.getClass());
	/*
	@Autowired
	FileService fileService;

	@Autowired
	private ProductImageRepository productImageRepository;

	
	public boolean addProduct(Product product, Category category, MultipartFile[] multipartFile, int venderid) {

		try {

			product.setCategory(category);
			product.setVid(venderid);
			Product savedProduct = productRepository.save(product);

			for (int filecount = 0; filecount < multipartFile.length; filecount++) {

				boolean savedImage = fileService.uploadFile(multipartFile[filecount], venderid, product);
				if (savedImage) {
					ProductImage productImage = new ProductImage();
					productImage.setProductImageName(multipartFile[filecount].getOriginalFilename());
					productImage.setProductId(product.getProductId());
					productImageRepository.save(productImage);
					System.out.println("file stored: " + multipartFile[filecount].getOriginalFilename());
				}

			}

			// Arrays.asList(multipartFile)
			// .stream()
			// .forEach(file -> fileService.uploadFile(file,venderid,product));

			System.out.println("product save successfully");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Product> displayImage() {
		List<Product> product = productRepository.findAll();
		return product;

	}

	public List<Product> displayProduct(int venderid) {
		List<Product> productlist = productRepository.findAllByVid(venderid);
		return productlist;

	}

	public Product get(long pid) {
		Product productdata = productRepository.findByPid(pid);
		return productdata;
	}


	public void save(Product product,Category category,int venderid) {

		product.setProductId(product.getProductId());
		product.setProductname(product.getProductname());
		product.setPrice(product.getPrice());
		product.setDescription(product.getDescription());
		product.setQuantity(product.getQuantity());
		product.setVid(venderid);
		product.setCategory(category);
		
		productRepository.save(product);
		
	}
	
	*/
	
	//  **************start********
	
	
	
	public void saveImage(Product product) {
		productRepository.save(product);	
	}

	public List<Product> getAllActiveImages() {
		return productRepository.findAll();
	}
	
	public List<Product> getAllImagesById(int venderid){
		return productRepository.findAllByVid(venderid);
	}
	

	public Optional<Product> getImageById(Long productId) {
		return productRepository.findById(productId);
	}
	
	@Transactional
	public void deleteProduct(Long productId) {
		productRepository.deleteByProductId(productId);
	}
		
	public Product findProduct(Long productId) {
		return productRepository.findByProductId(productId);
	}
	
	
	
	//   *********end*********
	

}















/*
 * package com.affiliate.product.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.web.multipart.MultipartFile;
 * 
 * import com.affiliate.product.Category; import com.affiliate.product.Product;
 * 
 * public interface ProductService { public boolean addProduct(Product
 * product,Category category,MultipartFile[] multipartFile,int venderid);
 * 
 * public List<Product> displayImage();
 * 
 * public List<Product> displayProduct(int venderid);
 * 
 * public Product get(long pid);
 * 
 * public void save(Product product,Category category,int venderid); }
 */







