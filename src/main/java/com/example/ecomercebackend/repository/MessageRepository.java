package com.example.ecomercebackend.repository;

import com.example.ecomercebackend.Model.Message;
import com.example.ecomercebackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderOrReceiver(User sender, User receiver);
}
