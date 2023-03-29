package com.example.ecomercebackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @MessageMapping("message")
 //   public void processMessage(@Payload Message message) {
 //       messagingTemplate.convertAndSend("/topic/" + message.getTo(),  message);
 //   }
}