package com.example.ecomercebackend.controller;

import com.example.ecomercebackend.Model.Payment;
import com.example.ecomercebackend.repository.PaymentRepository;
import com.example.ecomercebackend.service.panierService;
import com.example.ecomercebackend.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Payment")
@RestController

public class PaymentController {


    @Autowired
    private panierService panierService;

    @Autowired
    private paymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("")
    public List<Payment> getAll()
    {
     return paymentRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment, @RequestParam Long productId) {
        Payment createdProduct = paymentService.createPayment(payment, productId);
        return ResponseEntity.ok(createdProduct);
    }

}
