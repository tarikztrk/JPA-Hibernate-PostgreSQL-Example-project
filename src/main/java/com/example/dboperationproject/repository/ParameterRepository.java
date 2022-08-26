package com.example.dboperationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dboperationproject.model.Parameter;
import com.example.dboperationproject.model.Phone;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, String> {
	Parameter getByCommandName(String commandName);
}
