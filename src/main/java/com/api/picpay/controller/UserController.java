package com.api.picpay.controller;

import com.api.picpay.dto.TransferRequest;
import com.api.picpay.dto.UserDTO;
import com.api.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try {
            UserDTO user = service.findUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO data) {
        try {
            service.saveUser(data);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO data, @PathVariable Long id) {
        try {
            service.updateUser(data, id);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> transferFunds(@RequestBody TransferRequest transferRequest) {
        try {
            service.transferFunds(transferRequest.getSenderId(), transferRequest.getRecipientId(), transferRequest.getValue());
            return ResponseEntity.status(HttpStatus.OK).body("Transfer completed successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
