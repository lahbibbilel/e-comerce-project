package com.example.ecomercebackend.repository;

import com.example.ecomercebackend.Model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
