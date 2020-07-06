package com.app.service;

import java.util.List;

import com.app.model.Customer;

public interface ICustomerService {

public Customer getCustomer(long accountNumber);
	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public String deleteCustomer(long accountNumber);
	
	public List<Customer> getallCustomer();
}
