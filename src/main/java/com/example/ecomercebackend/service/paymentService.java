package com.example.ecomercebackend.service;

import com.example.ecomercebackend.Model.Payment;
import com.example.ecomercebackend.Model.Product;
import com.example.ecomercebackend.repository.ClientRepository;
import com.example.ecomercebackend.repository.PanierRepository;
import com.example.ecomercebackend.repository.PaymentRepository;
import com.example.ecomercebackend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class paymentService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    public Payment createPayment(Payment payment, Long productId) {
        Product panier = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        payment.setProduct(panier);
        return paymentRepository.save(payment);
    }


}
