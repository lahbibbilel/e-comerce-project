package com.example.ecomercebackend.service;

import com.example.ecomercebackend.Model.Client;
import com.example.ecomercebackend.Model.Product;
import com.example.ecomercebackend.repository.ClientRepository;
import com.example.ecomercebackend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository produitRepository;

    @Autowired
    private ClientRepository clientRepository;



    public ProductService(ProductRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Product> getAllProduits() {
        return produitRepository.findAll();
    }

    public Product getProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable avec l'ID : " + id));
    }


    public Product getProduitByNom(String nom) {
        return produitRepository.findFirstByNom(nom);
    }




    public Product createProduit(Product produit) {


        return produitRepository.save(produit);
    }


    public Product createProduct(Product product, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        product.setClient(client);
        return produitRepository.save(product);
    }

    public Product createProductSansClient(Product product, MultipartFile file) throws IOException {
        Client client = product.getClient();
        if (client == null) {
            throw new EntityNotFoundException("Client not found");
        }

        if (file != null && !file.isEmpty()) {
            byte[] imageBytes = file.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            product.setImage(base64Image.getBytes());
        }

        return produitRepository.save(product);
    }



    public Product createProductIm(Product product, Long clientId, MultipartFile file) throws IOException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        product.setClient(client);

        if (file != null && !file.isEmpty()) {
            byte[] imageBytes = file.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            product.setImage(base64Image.getBytes());
        }

        return produitRepository.save(product);
    }




    public Product createProductByEmail(Product product, String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        product.setClient(client);
        return produitRepository.save(product);
    }



    public Product updateProduit(Long id, Product produitDetails) {
        Product produit = getProduitById(id);

        produit.setNom(produitDetails.getNom());
        produit.setDescription(produitDetails.getDescription());
        produit.setPrix(produitDetails.getPrix());
        produit.setImage(produitDetails.getImage());

        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id) {
        Product produit = getProduitById(id);
        produitRepository.delete(produit);
    }

    public List<Product> getProductsByClientId(Long clientId) {
        return produitRepository.findByClientId(clientId);
    }





}















