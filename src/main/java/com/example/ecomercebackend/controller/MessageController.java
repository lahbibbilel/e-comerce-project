package com.example.ecomercebackend.controller;

import com.example.ecomercebackend.Model.Message;
import com.example.ecomercebackend.repository.MessageRepository;
import com.example.ecomercebackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("")
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable("id") Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new NotFoundException("Message not found"));
    }

    @PostMapping("")
    public Message createMessage(@Valid @RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam("sender") User sender, @RequestParam("receiver") User receiver) {
        return messageRepository.findBySenderOrReceiver(sender, receiver);
    }
}

