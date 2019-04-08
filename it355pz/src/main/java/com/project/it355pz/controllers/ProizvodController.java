package com.project.it355pz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.it355pz.dto.request.ProductDto;
import com.project.it355pz.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProizvodController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
		ProductDto product = productService.addProduct(productDto);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> products = productService.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PutMapping("/remove/{idProduct}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProductDto> removeProduct(@PathVariable int idProduct){
		ProductDto deletedProduct = productService.deleteProduct(idProduct);
		return new ResponseEntity<>(deletedProduct,HttpStatus.OK);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<ProductDto>> getAllByCategory(@RequestParam String category){
		List<ProductDto> products = productService.getAllByCategory(category);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable int id){
		ProductDto product = productService.getProductById(id);
		return new ResponseEntity<>(product,HttpStatus.OK);

	}
}
