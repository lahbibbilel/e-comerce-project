package com.example.ecomercebackend.repository;


import com.example.ecomercebackend.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByEmail(String email);
    List<Client> findByName(String name);

    List<Client> findByNameIgnoreCase(String clientName);
}
