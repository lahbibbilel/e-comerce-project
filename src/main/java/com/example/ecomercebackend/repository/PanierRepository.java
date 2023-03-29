package com.example.ecomercebackend.repository;
import com.example.ecomercebackend.Model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface PanierRepository extends JpaRepository<Panier, Long> {
    List<Panier> findByClientId(Long clientId);

}
