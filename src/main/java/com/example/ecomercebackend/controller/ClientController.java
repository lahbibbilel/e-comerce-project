package com.example.ecomercebackend.controller;

import com.example.ecomercebackend.Model.Client;
import com.example.ecomercebackend.repository.ClientRepository;
import com.example.ecomercebackend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @PostMapping("")
    public Client createClient(@RequestBody Client user) {
        return clientRepository.save(user);
    }

   @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client user) {
        Client existingUser = clientRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            clientRepository.save(existingUser);
        }
        return existingUser;
    }


    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
@Autowired
private PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestParam String email, @RequestParam String password) {
        Optional<Client> optionalClient = clientRepository.findByEmail(email);
        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            if (bCryptPasswordEncoder.matches(password, existingClient.getPassword())) {
                return new ResponseEntity<>(existingClient, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestBody Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<Client> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Client> optionalClient = clientRepository.findByEmail(authentication.getName());
        if (optionalClient.isPresent()) {
            return new ResponseEntity<>(optionalClient.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}