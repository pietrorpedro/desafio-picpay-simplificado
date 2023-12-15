package com.api.picpay.service;

import com.api.picpay.dto.RoleEnum;
import com.api.picpay.dto.UserDTO;
import com.api.picpay.model.User;
import com.api.picpay.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public void transferFunds(Long senderId, Long recipientId, Double value) throws Exception {
        User sender = repository.findById(senderId).orElseThrow(() -> new EntityNotFoundException("Sender not found"));
        User recipient = repository.findById(recipientId).orElseThrow(() -> new EntityNotFoundException("Recipient not found"));

        if (sender.getRole().name().equals("SHOPKEEPER")) {
            throw new Exception("Shopkeepers cannot transfer funds");
        }else {
            sender.setBalance(sender.getBalance() - value);
            recipient.setBalance(recipient.getBalance() + value);

            repository.save(sender);
            repository.save(recipient);
        }
    }
}
