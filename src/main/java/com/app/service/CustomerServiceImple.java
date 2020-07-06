package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ICustomerDao;
import com.app.model.Customer;

@Service
public class CustomerServiceImple implements ICustomerService {

	@Autowired
	ICustomerDao dao;
	
	@Override
	public Customer getCustomer(long accountNumber) {
		return dao.getCustomer(accountNumber);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return dao.addCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return dao.updateCustomer(customer);
	}

	@Override
	public String deleteCustomer(long accountNumber) {
		return dao.deleteCustomer(accountNumber);
	}

	@Override
	public List<Customer> getallCustomer() {
		return dao.getallCustomer();
	}

}
