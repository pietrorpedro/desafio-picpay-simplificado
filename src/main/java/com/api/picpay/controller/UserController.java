package com.api.picpay.controller;

import com.api.picpay.dto.TransactionDTO;
import com.api.picpay.dto.UserDTO;
import com.api.picpay.model.Transaction;
import com.api.picpay.service.TransactionService;
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
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try {
            UserDTO user = userService.findUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO data) {
        try {
            userService.saveUser(data);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO data, @PathVariable Long id) {
        try {
            userService.updateUser(data, id);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/transfer")
    public ResponseEntity<?> makeTransation(@RequestBody Transaction transaction) {
        try {
            userService.transferFunds(transaction.getPayer(), transaction.getPayee(), transaction.getValue());
            transactionService.saveTransaction(new TransactionDTO(transaction.getPayer(), transaction.getPayee(), transaction.getValue()));
            return ResponseEntity.status(HttpStatus.OK).body("Transfer completed successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
