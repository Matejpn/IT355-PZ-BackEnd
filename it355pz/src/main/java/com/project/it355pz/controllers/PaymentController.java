package com.project.it355pz.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.it355pz.dto.request.ItemsDto;
import com.project.it355pz.dto.request.PaymentDto;
import com.project.it355pz.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PaymentDto>> getAllPayments(){
		List<PaymentDto> payments = paymentService.findAllPayments();
		return new ResponseEntity<>(payments,HttpStatus.OK);
	}
	
	@PostMapping("/buy")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PaymentDto> buyItems(@RequestBody ItemsDto items, Principal principal){
		PaymentDto payment = paymentService.buyItems(items, principal.getName());
		return new ResponseEntity<>(payment,HttpStatus.OK);
	}

}
