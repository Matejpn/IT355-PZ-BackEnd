package com.project.it355pz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.it355pz.dto.request.ProductDto;
import com.project.it355pz.model.Brand;
import com.project.it355pz.model.Category;
import com.project.it355pz.model.Product;
import com.project.it355pz.repository.BrandRepository;
import com.project.it355pz.repository.CategoryRepository;
import com.project.it355pz.repository.ProductRepository;
import com.project.it355pz.service.ProductService;
import com.project.it355pz.util.ShoppingException;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	BrandRepository brandRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public ProductDto addProduct(ProductDto productDto) {
		if(productDto.getName() ==null || productDto.getName()=="" || productDto.getBrand()==null ||productDto.getBrand()=="" || productDto.getCategory()==null || productDto.getCategory()=="" || productDto.getUrl()==null || productDto.getUrl()=="") {
			throw new ShoppingException(HttpStatus.BAD_REQUEST, "All fields must be filled!");
		}
		if(productDto.getPrice() <= 0) {
			throw new ShoppingException(HttpStatus.BAD_REQUEST, "Price must be greater than 0.");
		}
		Brand brand = brandRepository.findByName(productDto.getBrand());
		Category category = categoryRepository.findByName(productDto.getCategory());
		if(brand == null) {
			brand = new Brand();
			brand.setName(productDto.getBrand());
			brandRepository.saveAndFlush(brand);
		}
		if(category == null) {
			category = new Category();
			category.setName(productDto.getCategory());
			categoryRepository.saveAndFlush(category);
		}
		Product product = new Product();
		product.setBrand(brand);
		product.setCategory(category);
		product.setDeleted(false);
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setUrl(productDto.getUrl());
		productRepository.saveAndFlush(product);
		productDto.setDeleted(false);
		productDto.setidProduct(product.getIdProduct());
		return productDto;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findByisDeletedFalse();
		List<ProductDto> productsDto = new ArrayList<>();
		for (Product product : products) {
			ProductDto newProduct = new ProductDto();
			newProduct.setBrand(product.getBrand().getName());
			newProduct.setCategory(product.getCategory().getName());
			newProduct.setName(product.getName());
			newProduct.setPrice(product.getPrice());
			newProduct.setUrl(product.getUrl());
			newProduct.setidProduct(product.getIdProduct());
			newProduct.setDeleted(product.isDeleted());
			productsDto.add(newProduct);
		}
		return productsDto;
	}

	@Override
	public ProductDto deleteProduct(int idProduct) {
		Product product = productRepository.findById(idProduct).orElseThrow(() -> new ShoppingException(HttpStatus.NOT_FOUND,"Wrong id!"));
		product.setDeleted(true);
		productRepository.save(product);
		ProductDto newProduct = new ProductDto();
		newProduct.setBrand(product.getBrand().getName());
		newProduct.setCategory(product.getCategory().getName());
		newProduct.setName(product.getName());
		newProduct.setPrice(product.getPrice());
		newProduct.setUrl(product.getUrl());
		newProduct.setidProduct(product.getIdProduct());
		newProduct.setDeleted(product.isDeleted());
		return newProduct;
	}

	@Override
	public List<ProductDto> getAllByCategory(String category) {
		List<Product> products = productRepository.findByCategory(false,category);
		List<ProductDto> productsDto = new ArrayList<>();
		for (Product product : products) {
			ProductDto newProduct = new ProductDto();
			newProduct.setBrand(product.getBrand().getName());
			newProduct.setCategory(product.getCategory().getName());
			newProduct.setName(product.getName());
			newProduct.setPrice(product.getPrice());
			newProduct.setUrl(product.getUrl());
			newProduct.setidProduct(product.getIdProduct());
			newProduct.setDeleted(product.isDeleted());
			productsDto.add(newProduct);
		}
		return productsDto;
	}

	@Override
	public ProductDto getProductById(int id) {
		Product product = productRepository.findById(id).orElseThrow(()-> new ShoppingException(HttpStatus.NOT_FOUND,"Wrong id!"));
		ProductDto responseProduct = new ProductDto();
		responseProduct.setBrand(product.getBrand().getName());
		responseProduct.setCategory(product.getCategory().getName());
		responseProduct.setName(product.getName());
		responseProduct.setPrice(product.getPrice());
		responseProduct.setUrl(product.getUrl());
		responseProduct.setidProduct(product.getIdProduct());
		responseProduct.setDeleted(product.isDeleted());
		return responseProduct;
	}

}
