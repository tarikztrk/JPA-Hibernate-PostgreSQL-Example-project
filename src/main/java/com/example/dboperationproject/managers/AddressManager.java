package com.example.dboperationproject.managers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.managers.Interfaces.IDbOperations;
import com.example.dboperationproject.model.Account;
import com.example.dboperationproject.model.Address;
import com.example.dboperationproject.model.Phone;
import com.example.dboperationproject.repository.AccountRepository;
import com.example.dboperationproject.repository.AddressRepository;
import com.example.dboperationproject.repository.PhoneRepository;

@Service
public class AddressManager //implements IDbOperations<Address> 
{

	private AddressRepository addressRepository;

	@Autowired
	public AddressManager(AddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}


	public List<Address> getAll() {
		return addressRepository.findAll();
	}


	public Optional<Address> getById(Long id) {
		return addressRepository.findById(id);
	}

	
	public Address create(Address entity) {
		return addressRepository.save(entity);
	}

	
	public Address update(Long addressId, Address addressReq) {
		return addressRepository.findById(addressId).map(address -> {
			address.setCountry(addressReq.getCountry());
			address.setCustomerId(addressReq.getCustomerId());
			address.setAddressLine(addressReq.getAddressLine());
			address.setCity(addressReq.getCity());
			address.setPostalCode(addressReq.getPostalCode());
			return addressRepository.save(address);
		}).orElseThrow(() -> new ResourceNotFoundException("Phone not found with id " + addressId));
	}

	
	public String delete(Long id) {

		try {
			Optional<Address> phone = addressRepository.findById(id);
			addressRepository.delete(phone.get());
			return "Silme işlemi başarılı";
		} catch (Exception e) {
			return "Silme işlemi başarısız";
		}

	}

}
