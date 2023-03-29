package com.example.ecomercebackend.controller;

import com.example.ecomercebackend.Model.Client;
import com.example.ecomercebackend.Model.Product;
import com.example.ecomercebackend.repository.ClientRepository;
import com.example.ecomercebackend.repository.ProductRepository;
import com.example.ecomercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/produits")
public class ProductController {

    private final ProductService produitService;
 @Autowired
private ProductRepository productRepository;
    public ProductController(ProductService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public List<Product> getAllProducts() throws IOException {
        List<Product> products = produitService.getAllProduits();
        for (Product product : products) {
            byte[] imageBytes = Base64.getDecoder().decode(product.getImage());
            product.setImage(imageBytes);
        }
        return products;
    }


    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/products")
    public List<Product> getProductsByClientName(@RequestParam String clientName) {
        List<Client> clients = clientRepository.findByName(clientName);
        List<Product> products = new ArrayList<>();
        for (Client client : clients) {
            products.addAll(client.getProducts());
        }
        return products;
    }


    @GetMapping("/{id}")
    public Product getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    @GetMapping("prod/{nom}")
    public Product getProduitByNom(@PathVariable String nom) {
        return (Product) produitService.getProduitByNom(nom);
    }



    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestParam Long clientId) {
        Product createdProduct = produitService.createProduct(product, clientId);
        return ResponseEntity.ok(createdProduct);
    }

    @PostMapping("/bymail")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestParam String email) {
        Product createdProduct = produitService.createProductByEmail(product, email);
        return ResponseEntity.ok(createdProduct);
    }



    @PostMapping("/image")
    public ResponseEntity<Product> createProductIm(@ModelAttribute Product product,
                                                 @RequestParam("clientId") Long clientId,
                                                 @RequestParam("file") MultipartFile file) throws IOException {
        Product createdProduct = produitService.createProductIm(product, clientId, file);
        return ResponseEntity.ok(createdProduct);
    }

    @PostMapping("/imageSansClient")
    public ResponseEntity<Product> createProductImage(@ModelAttribute Product product,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        Product createdProduct = produitService.createProductSansClient(product, file);
        return ResponseEntity.ok(createdProduct);
    }




    @PutMapping("/{id}")
    public Product updateProduit(@PathVariable Long id, @RequestBody Product produit) {
        return produitService.updateProduit(id, produit);
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }


}
