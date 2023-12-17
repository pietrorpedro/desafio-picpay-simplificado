package com.api.picpay.service;

import com.api.picpay.dto.TransactionDTO;
import com.api.picpay.model.Transaction;
import com.api.picpay.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public TransactionDTO findTransactionById(Long id) {
        Transaction transaction = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
        return new TransactionDTO(transaction.getPayer(), transaction.getPayee(), transaction.getValue());
    }

    public void saveTransaction(TransactionDTO data) throws Exception {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(data, transaction);

        try {
            repository.save(transaction);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
