package com.example.dboperationproject.managers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dboperationproject.managers.Interfaces.IExecutor;
import com.example.dboperationproject.model.Parameter;
import com.example.dboperationproject.model.XBag;
import com.example.dboperationproject.repository.AccountRepository;
import com.example.dboperationproject.repository.AddressRepository;
import com.example.dboperationproject.repository.CustomerRepository;
import com.example.dboperationproject.repository.ParameterRepository;
import com.example.dboperationproject.repository.PhoneRepository;

@Service
public class Executor implements IExecutor {

	@Autowired
	ParameterRepository parameterRepository;

	public Executor(ParameterRepository parameterRepository, AddressRepository addressRepository,
			AccountRepository accountRepository) {
		super();
		this.parameterRepository = parameterRepository;
		this.addressRepository = addressRepository;
		this.accountRepository = accountRepository;
	}

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	public ParameterRepository getParameterRepository() {
		return parameterRepository;
	}

	public void setParameterRepository(ParameterRepository parameterRepository) {
		this.parameterRepository = parameterRepository;
	}

	@Override
	public XBag execute(String commandName, XBag basket) {

		Parameter parameter = parameterRepository.getByCommandName(commandName);
		String className = parameter.getClassName();
		String methodName = parameter.getMethodName();

		try {

			Class<?> clazz = Class.forName(className);

			// Class<?> instanceRepo = findInstanceRepo(className);
//
//			Constructor<?> cons = clazz.getDeclaredConstructor(instanceRepo);
			Object obj = createInstance(className,clazz);
//			cons.newInstance(instanceRepo);

//			System.out.println(cons);
			Method m = clazz.getDeclaredMethod(methodName, XBag.class);

			java.lang.reflect.Parameter[] parameters = m.getParameters();

			for (java.lang.reflect.Parameter parameter2 : parameters) {
				System.out.println(parameter2.getType());
				System.out.println(parameter2.getName());
			}

			System.out.println(m.getParameters());
			return (XBag)m.invoke(obj, basket);

		} catch (Exception e) {
			System.out.println("dsfdsfds");
		}
		return null;
	}

	public Object createInstance(String className, Class<?> clazz) {
		Constructor<?> cons = null;
		Object obj = null;
		try {
			if (className.contains("Account")) {
				cons = clazz.getDeclaredConstructor(AccountRepository.class);
				obj = cons.newInstance(accountRepository);
			}

			if (className.contains("Address")) {
				cons = clazz.getDeclaredConstructor(AddressRepository.class);
				obj = cons.newInstance(addressRepository);
			}
		} catch (Exception e) {
			System.out.println("hata oluştu");
		}

//		if (className.contains("Customer")) {
//			instanceClass = CustomerRepository.class;
//		}
//		if (className.contains("Phone")) {
//			instanceClass = PhoneRepository.class;
//		}
		return obj;
	}

}
