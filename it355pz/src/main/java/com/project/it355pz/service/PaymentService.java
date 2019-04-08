package com.project.it355pz.service;

import java.util.List;

import com.project.it355pz.dto.request.ItemsDto;
import com.project.it355pz.dto.request.PaymentDto;

public interface PaymentService {

	List<PaymentDto> findAllPayments();

	PaymentDto buyItems(ItemsDto items, String email);

}
