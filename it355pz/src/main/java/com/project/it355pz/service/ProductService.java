package com.project.it355pz.service;

import java.util.List;

import com.project.it355pz.dto.request.ProductDto;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto);

	List<ProductDto> getAllProducts();

	ProductDto deleteProduct(int idProduct);

	List<ProductDto> getAllByCategory(String category);

	ProductDto getProductById(int id);

}
