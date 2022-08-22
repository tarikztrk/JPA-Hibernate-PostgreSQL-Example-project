package com.example.dboperationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dboperationproject.model.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
