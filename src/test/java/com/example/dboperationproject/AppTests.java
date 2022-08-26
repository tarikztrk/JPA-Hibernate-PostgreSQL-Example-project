package com.example.dboperationproject;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dboperationproject.managers.Executor;
import com.example.dboperationproject.managers.Interfaces.IExecutor;
import com.example.dboperationproject.model.Account;
import com.example.dboperationproject.model.NameValue;
import com.example.dboperationproject.model.XBag;
import com.example.dboperationproject.repository.AccountRepository;
import com.example.dboperationproject.repository.AddressRepository;
import com.example.dboperationproject.repository.ParameterRepository;

@SpringBootTest
public class AppTests {

	@Autowired
	ParameterRepository parameterRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	AccountRepository accountRepository;

	@Test
	public void getAllAccounts() {
		IExecutor executor = new Executor(parameterRepository, addressRepository, accountRepository);
		XBag bag = new XBag();
		bag.put("id", (long) 1000, bag);

		XBag retVal = executor.execute("getAllAccounts", bag);

		for (NameValue item : retVal.getBasket()) {
			List<Account> account = (List<Account>) item.getValue();

			for (Account acc : account) {
				System.out.println(acc.getAccountNumber());
				System.out.println(acc.getOwnerBranchCode());
				System.out.println(acc.getOwnerBranchName());
				System.out.println(acc.getAccountId());
				System.out.println(acc.getCustomerId());
			}
		}
	}

	@Test
	public void getAccount() {
		IExecutor executor = new Executor(parameterRepository, addressRepository, accountRepository);
		XBag bag = new XBag();
		bag.put("id", (long) 1000, bag);

		XBag retVal = executor.execute("getAccount", bag);

		for (NameValue item : retVal.getBasket()) {
			Account account = (Account) item.getValue();

			System.out.println(account.getAccountNumber());
			System.out.println(account.getOwnerBranchCode());
			System.out.println(account.getOwnerBranchName());
			System.out.println(account.getAccountId());
			System.out.println(account.getCustomerId());
		}
	}
	
	@Test
	public void saveAccount() {
		IExecutor executor = new Executor(parameterRepository, addressRepository, accountRepository);
		XBag bag = new XBag();
		bag.put("accountNumber", (long) 1000, bag);
		bag.put("customerId", (long) 1000, bag);
		bag.put("branchCode", (int) 1000, bag);
		bag.put("branchName", (String) "sddsad", bag);

		XBag retVal = executor.execute("saveAccount", bag);

		for (NameValue item : retVal.getBasket()) {
			Account account = (Account) item.getValue();

			System.out.println(account.getAccountNumber());
			System.out.println(account.getOwnerBranchCode());
			System.out.println(account.getOwnerBranchName());
			System.out.println(account.getAccountId());
			System.out.println(account.getCustomerId());
		}
	}

}
