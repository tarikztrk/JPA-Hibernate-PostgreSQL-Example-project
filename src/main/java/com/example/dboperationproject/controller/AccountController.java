package com.example.dboperationproject.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.model.Account;
import com.example.dboperationproject.repository.AccountRepository;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
    
    @GetMapping("/account/{id}")
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }


    @PostMapping("/addAccount")
    public Account createAccount(@Valid @RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("/account/{accountId}")
    public Account updateAccount(@PathVariable Long accountId,
                                   @Valid @RequestBody Account accountReq) {
        return accountRepository.findById(accountId)
                .map(account -> {
                	account.setCountryCode(accountReq.getCountryCode());
                	account.setCustomerId(accountReq.getCustomerId());
                	account.setOwnerBranchCode(accountReq.getOwnerBranchCode());
                	account.setOwnerBranchName(accountReq.getOwnerBranchName());
                    return accountRepository.save(account);
                }).orElseThrow(() -> new ResourceNotFoundException("Phone not found with id " + accountId));
    }
    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        return accountRepository.findById(accountId)
                .map(account -> {
                	accountRepository.delete(account);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("customer not found with id " + accountId));
    }
}
