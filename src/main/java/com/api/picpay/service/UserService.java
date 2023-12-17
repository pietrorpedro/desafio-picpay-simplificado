package com.api.picpay.service;

import com.api.picpay.dto.RoleEnum;
import com.api.picpay.dto.UserDTO;
import com.api.picpay.model.User;
import com.api.picpay.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO findUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UserDTO(user.getFullName(), user.getCPF(), user.getEmail(), user.getPassword(), user.getBalance(), user.getRole());
    }


    public void saveUser(UserDTO data) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(data, user);

        try {
            repository.save(user);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateUser(UserDTO data, Long id) throws Exception {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        BeanUtils.copyProperties(data, user);

        try {
            repository.save(user);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public void transferFunds(Long payerId, Long payeeId, Double value) throws Exception {
        User payer = repository.findById(payerId).orElseThrow(() -> new EntityNotFoundException("Payer not found"));
        User payee = repository.findById(payeeId).orElseThrow(() -> new EntityNotFoundException("Payee not found"));

        if (payer.getRole().name().equals("SHOPKEEPER")) {
            throw new Exception("Shopkeepers cannot transfer funds");
        }
        if (payer.getBalance() <= 0 || payer.getBalance() < value) {
            throw new Exception("Insufficient fund");
        }

        String authorizationService = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
        ResponseEntity<Map> response = new RestTemplate().getForEntity(authorizationService, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            if ("Autorizado".equalsIgnoreCase(message)) {
                payer.setBalance(payer.getBalance() - value);
                payee.setBalance(payee.getBalance() + value);

                repository.save(payer);
                repository.save(payee);
            }else {
                throw new Exception("Transfer authorization failed");
            }
        }else {
            throw new Exception("Transfer authorization contact failed");
        }

    }
}
