package com.example.ecomercebackend.Model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String firstname;
    private String email;
    private String password;


    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public Client(Long id, String name, String firstname, String email, String password) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.password = password;

    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }



    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Panier> paniers;

}
