package com.example.ecomercebackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository {


    Optional findByEmail(String email);
}
