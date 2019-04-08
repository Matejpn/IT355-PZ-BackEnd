package com.project.it355pz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.it355pz.dto.request.ItemDto;
import com.project.it355pz.dto.request.ItemsDto;
import com.project.it355pz.dto.request.PaymentDto;
import com.project.it355pz.dto.request.ProductDto;
import com.project.it355pz.dto.response.PaymentProductDto;
import com.project.it355pz.model.Item;
import com.project.it355pz.model.Payment;
import com.project.it355pz.model.Product;
import com.project.it355pz.model.User;
import com.project.it355pz.repository.ItemRepository;
import com.project.it355pz.repository.PaymentRepository;
import com.project.it355pz.repository.ProductRepository;
import com.project.it355pz.repository.UserRepository;
import com.project.it355pz.service.PaymentService;
import com.project.it355pz.util.ShoppingException;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<PaymentDto> findAllPayments() {
		List<Payment> payments = paymentRepository.findAll();
		List<PaymentDto> paymentsDto = new ArrayList<>();
		for (Payment payment : payments) {
			PaymentDto newPayment = new PaymentDto();
			newPayment.setDate(payment.getDate());
			newPayment.setUserName(payment.getUser().getFirstname());
			newPayment.setUserSurname(payment.getUser().getLastname());
			newPayment.setIdPayment(payment.getIdPayment());
			List<PaymentProductDto> products = new ArrayList<>();
			for (Item item : payment.getItems()) {
				PaymentProductDto productDto = new PaymentProductDto();
				productDto.setProductName(item.getProduct().getName());
				productDto.setProductPrice(item.getProduct().getPrice());
				productDto.setQuantity(item.getQuantity());
				products.add(productDto);
			}
			newPayment.setProducts(products);
			paymentsDto.add(newPayment);
			}
		return paymentsDto;
	}

	@Override
	@Transactional
	public PaymentDto buyItems(ItemsDto items, String email) {
		User user = userRepository.findByEmail(email);
		PaymentDto paymentResponse = new PaymentDto();
		
		Payment payment = new Payment();
		payment.setDate(new Date());
		payment.setUser(user);
		paymentRepository.saveAndFlush(payment);
		paymentResponse.setIdPayment(payment.getIdPayment());
		paymentResponse.setDate(payment.getDate());
		paymentResponse.setUserName(payment.getUser().getFirstname());
		paymentResponse.setUserSurname(payment.getUser().getLastname());
		List<PaymentProductDto> products = new ArrayList<>();
		for (ItemDto itemDto : items.getItems()) {
			Item item = new Item();
			item.setPayment(payment);
			item.setQuantity(itemDto.getQuantity());
			Product product = productRepository.findById(itemDto.getIdProduct()).orElseThrow(() -> new ShoppingException(HttpStatus.NOT_FOUND, "Wrong product id!"));
			item.setProduct(product);
			itemRepository.saveAndFlush(item);
			
			PaymentProductDto newProduct = new PaymentProductDto();
			newProduct.setProductName(item.getProduct().getName());
			newProduct.setProductPrice(item.getProduct().getPrice());
			newProduct.setQuantity(item.getQuantity());
			products.add(newProduct);
		}
		paymentResponse.setProducts(products);
		return paymentResponse;
	}

}
