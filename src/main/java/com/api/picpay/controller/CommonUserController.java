package com.api.picpay.controller;

import com.api.picpay.dto.CommonUserDTO;
import com.api.picpay.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/common")
public class CommonUserController {

    @Autowired
    private CommonUserService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findCommonUserById(@PathVariable Long id) {
        try {
            CommonUserDTO user = service.findCommomUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveCommonUser(@RequestBody CommonUserDTO data) {
        try {
            service.saveCommonUser(data);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommonUser(@RequestBody CommonUserDTO data, @PathVariable Long id) {
        try {
            service.updateCommonUser(data, id);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommonUser(@PathVariable Long id) {
        try {
            service.deleteCommonUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
