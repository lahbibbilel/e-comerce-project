package com.example.ecomercebackend.repository;

import com.example.ecomercebackend.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
