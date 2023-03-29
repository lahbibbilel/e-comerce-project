package com.example.ecomercebackend.service;

import com.example.ecomercebackend.Model.Client;
import com.example.ecomercebackend.Model.Product;
import com.example.ecomercebackend.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    private Product[] products;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        getClientById(id); // vérifie que le client existe
        client.setId(id);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        getClientById(id); // vérifie que le client existe
        clientRepository.deleteById(id);
    }

    public Collection<? extends Product> getProducts(String clientName) {
        List<Product> filteredProducts = new ArrayList<>();
        List<Client> clients = clientRepository.findByNameIgnoreCase(clientName);
        for (Client client : clients) {
            filteredProducts.addAll(client.getProducts());
        }
        return filteredProducts;
    }



}

