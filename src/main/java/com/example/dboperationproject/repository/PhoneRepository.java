package com.example.dboperationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dboperationproject.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
