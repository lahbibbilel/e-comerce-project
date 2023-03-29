package com.example.ecomercebackend.repository;

import com.example.ecomercebackend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface ProductRepository
    extends JpaRepository<Product, Long> {
    List<Product> findByClientId(Long clientId);

    Product findFirstByNom(String nom);

}
