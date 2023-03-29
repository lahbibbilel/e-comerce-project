package com.example.ecomercebackend.controller;

import com.example.ecomercebackend.Model.Panier;
import com.example.ecomercebackend.Model.Product;
import com.example.ecomercebackend.service.ProductService;
import com.example.ecomercebackend.service.panierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Panier")
@RestController
public class PanierController {
        @Autowired
        private panierService panierService;

        @Autowired
        private ProductService productService;



    @PostMapping("")
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier, @RequestParam Long productId, @RequestParam String email) {
        Panier createdProduct = panierService.createProduct(panier, productId, email);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/client/{clientId}/products")
    public ResponseEntity<List<Panier>> AffichagePanier(@PathVariable Long clientId) {
        List<Panier> paniers = panierService.getPaniersByClientId(clientId);
        return ResponseEntity.ok(paniers);
    }

    @GetMapping("/client/{clientId}/products")
    public ResponseEntity<List<Product>> getClientProducts(@PathVariable Long clientId) {
        List<Product> products = productService.getProductsByClientId(clientId);
        return ResponseEntity.ok(products);
    }


}



