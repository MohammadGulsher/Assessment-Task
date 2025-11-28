package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entity.Product;


public interface ProductService {
	
	public Product saveProduct(Product product);
	
	public Product getByIdProduct(int orderId);
	
	public List<Product> getAllProduct();

}
