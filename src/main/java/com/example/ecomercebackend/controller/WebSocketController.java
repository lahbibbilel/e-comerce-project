package com.example.ecomercebackend.controller;


import com.example.ecomercebackend.Model.ChatMessage;
import com.example.ecomercebackend.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository messageRepository;

    @MessageMapping("/chat")
    public void handleMessage(@Payload ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<ChatMessage> getMessages() {
        return messageRepository.findAll();
    }
}