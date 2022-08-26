package com.example.dboperationproject.managers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.managers.Interfaces.IDbOperations;
import com.example.dboperationproject.model.Account;
import com.example.dboperationproject.model.NameValue;
import com.example.dboperationproject.model.XBag;
import com.example.dboperationproject.repository.AccountRepository;

@Service
public class AccountManager implements IDbOperations {

	private AccountRepository accountRepository;

	@Autowired
	public AccountManager(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public XBag getAll(XBag bag) {
		XBag retVal = new XBag();
		try {
			List<Account> serviceResponse = accountRepository.findAll();
			retVal.put("getAllResponse", serviceResponse,retVal);

		} catch (Exception e) {
			System.out.println("hesaplar getirilirken hata oluştu");
		}

		return retVal;

	}

	@Override
	public XBag getById(XBag bag) {

		XBag retVal = new XBag();
		try {
			Long id = (long) bag.findByKey("id", bag).getValue();

			Account serviceResponse = accountRepository.findById(id).get();

			retVal.put("getByIdResponse", serviceResponse,retVal);
		} catch (Exception e) {
			System.out.println("hesap bilgisi getirilirken hata oluştu");
		}

		return retVal;

	}

	@Override
	public XBag create(XBag bag) {
		XBag retVal = new XBag();

		try {
			Account entity = new Account();
			entity.setAccountNumber((long) bag.findByKey("accountNumber", bag).getValue());
			entity.setCustomerId((long) bag.findByKey("customerId", bag).getValue());
			entity.setOwnerBranchCode((int) bag.findByKey("branchCode", bag).getValue());
			entity.setOwnerBranchName((String) bag.findByKey("branchName", bag).getValue());
			Account serviceResponse = accountRepository.save(entity);

			retVal.put("createResponse", serviceResponse,retVal);
		} catch (Exception e) {
			System.out.println("create işlemi başarısız");
		}

		return retVal;
	}

	@Override
	public XBag update(XBag bag) {
		XBag retVal = new XBag();
		try {
			Account serviceResponse = accountRepository.findById((long) bag.findByKey("id", bag).getValue())
					.map(account -> {
						account.setAccountNumber((long) bag.findByKey("accountNumber", bag).getValue());
						account.setCustomerId((long) bag.findByKey("customerId", bag).getValue());
						account.setOwnerBranchCode((int) bag.findByKey("branchCode", bag).getValue());
						account.setOwnerBranchName((String) bag.findByKey("branchName", bag).getValue());
						return accountRepository.save(account);
					}).orElseThrow(() -> new ResourceNotFoundException("Phone not found"));

			retVal.put("updateResponse", serviceResponse,retVal);

		} catch (Exception e) {
			System.out.println("güncellemee işlemi başarısız");
		}

		return retVal;
	}

	@Override
	public XBag delete(XBag bag) {
		XBag retVal = new XBag();
		try {
			Optional<Account> account = accountRepository.findById((long) bag.findByKey("id", bag).getValue());
			accountRepository.delete(account.get());
			retVal.put("updateResponse", "Silme işlemi başarılı",retVal);
		} catch (Exception e) {
			retVal.put("updateResponse", "Silme işlemi başarısız",retVal);
		}
		return retVal;
	}

}
