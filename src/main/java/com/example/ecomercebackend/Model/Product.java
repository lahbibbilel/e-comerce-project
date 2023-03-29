package com.example.ecomercebackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "produit")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;

    private double prix;

    @Lob
    @Column(name = "image", columnDefinition="LONGBLOB")
    private byte[] image;

    // Constructeurs, getters et setters

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Panier> paniers;

    public Product(Long id, String nom, String description, double prix, byte[] image, Client client) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.client = client;
    }

    public Product() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }

    public void setPrix(double prix) { this.prix = prix; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    public Client getClient() { return client; }

    public void setClient(Client client) {
        this.client = client;
        client.getProducts().add(this);
    }

    // Autres méthodes si nécessaire
}
