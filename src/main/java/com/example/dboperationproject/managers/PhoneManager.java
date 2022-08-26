package com.example.dboperationproject.managers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.managers.Interfaces.IDbOperations;
import com.example.dboperationproject.model.Account;
import com.example.dboperationproject.model.Phone;
import com.example.dboperationproject.repository.AccountRepository;
import com.example.dboperationproject.repository.PhoneRepository;

@Service
public class PhoneManager //implements IDbOperations<Phone> 
{

	private PhoneRepository phoneRepository;

	@Autowired
	public PhoneManager(PhoneRepository phoneRepository) {
		super();
		this.phoneRepository = phoneRepository;
	}

	public List<Phone> getAll() {
		return phoneRepository.findAll();
	}

	public Optional<Phone> getById(Long id) {
		return phoneRepository.findById(id);
	}

	public Phone create(Phone entity) {
		return phoneRepository.save(entity);
	}

	public Phone update(Long phoneId, Phone phoneReq) {
		return phoneRepository.findById(phoneId).map(phone -> {
			phone.setCountryCode(phoneReq.getCountryCode());
			phone.setCustomerId(phoneReq.getCustomerId());
			phone.setNumber(phoneReq.getNumber());
			return phoneRepository.save(phone);
		}).orElseThrow(() -> new ResourceNotFoundException("Phone not found with id " + phoneId));
	}

	public String delete(Long id) {

		try {
			Optional<Phone> phone = phoneRepository.findById(id);
			phoneRepository.delete(phone.get());
			return "Silme işlemi başarılı";
		} catch (Exception e) {
			return "Silme işlemi başarısız";
		}

	}

}
