package com.example.dboperationproject.controller;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.model.Phone;
import com.example.dboperationproject.repository.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/phones")
    public List<Phone> getPhones() {
        return phoneRepository.findAll();
    }
    
    @GetMapping("/phones/{id}")
    public Optional<Phone> getPhone(Long id) {
        return phoneRepository.findById(id);
    }


    @PostMapping("/addPhone")
    public Phone createPhone(@Valid @RequestBody Phone phone) {
        return phoneRepository.save(phone);
    }

    @PutMapping("/phone/{phoneId}")
    public Phone updatePhone(@PathVariable Long phoneId,
                                   @Valid @RequestBody Phone phoneReq) {
        return phoneRepository.findById(phoneId)
                .map(phone -> {
                	phone.setCountryCode(phoneReq.getCountryCode());
                	phone.setNumber(phoneReq.getNumber());
                	phone.setCustomerId(phoneReq.getCustomerId());
                    return phoneRepository.save(phone);
                }).orElseThrow(() -> new ResourceNotFoundException("Phone not found with id " + phoneId));
    }
    @DeleteMapping("/phone/{id}")
    public ResponseEntity<?> deletePhone(@PathVariable Long phoneId) {
        return phoneRepository.findById(phoneId)
                .map(phone -> {
                	phoneRepository.delete(phone);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("customer not found with id " + phoneId));
    }
}
