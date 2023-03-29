package com.example.ecomercebackend.service;

import com.example.ecomercebackend.Model.Client;
import com.example.ecomercebackend.Model.Panier;
import com.example.ecomercebackend.Model.Product;
import com.example.ecomercebackend.repository.ClientRepository;
import com.example.ecomercebackend.repository.PanierRepository;
import com.example.ecomercebackend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class panierService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private ProductRepository productRepository;


    public Panier createProduct(Panier panier, Long productId,String email) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        panier.setClient(client);
        panier.setProduct(product);
        return panierRepository.save(panier);
    }


    public List<Panier> getPaniersByClientId(Long clientId) {
        return panierRepository.findByClientId(clientId);
    }


}
