package com.example.dboperationproject.controller;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.model.Address;
import com.example.dboperationproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping("/addresses")
	public List<Address> getAddresses() {
		return addressRepository.findAll();
	}

	@GetMapping("/address/{id}")
	public Optional<Address> getAddress(Long id) {
		return addressRepository.findById(id);
	}

	@PostMapping("/addAddress")
	public Address createAddress(@Valid @RequestBody Address address) {
		return addressRepository.save(address);
	}

	@PutMapping("/address/{addressId}")
	public Address updateAddress(@PathVariable Long addressId, @Valid @RequestBody Address addressReq) {
		return addressRepository.findById(addressId).map(address -> {
			address.setAddressLine(addressReq.getAddressLine());
			address.setCity(addressReq.getCity());
			address.setCustomerId(addressReq.getCustomerId());
			address.setCountry(addressReq.getCountry());
			address.setPostalCode(addressReq.getPostalCode());
			return addressRepository.save(address);
		}).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId));
	}

	@DeleteMapping("/address/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
		return addressRepository.findById(addressId).map(address -> {
			addressRepository.delete(address);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("customer not found with id " + addressId));
	}
}
