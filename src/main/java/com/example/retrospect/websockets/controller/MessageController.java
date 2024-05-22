package com.example.retrospect.websockets.controller;


import com.example.retrospect.websockets.entity.Message;
import com.example.retrospect.websockets.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*")
public class MessageController {

    private final MessageService messageService;

    @CrossOrigin("*")

    @GetMapping("/message/{room}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String room) {
        return ResponseEntity.ok(messageService.getAllMessages(room));
    }

    @DeleteMapping("/message/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        try{
            messageService.deleteMessageById(id);
            return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>("Message not found", HttpStatus.NOT_FOUND);
        }
    }
}