package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Product;
import com.example.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		product.setStatus("Pending");
		LocalDateTime date= LocalDateTime.now();
		product.setCreatedAt(date);
		Product pro=productRepository.save(product);
		return pro;
	}

	@Override
	public Product getByIdProduct(int orderId) {
		Product p=productRepository.findById(orderId).orElse(null);
		return p;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> all=productRepository.findAll();
		return all;
	}

}
