package com.app.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.model.Customer;

public interface ICustomerDao {
	
	public Customer getCustomer(long accountNumber);
	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public String deleteCustomer(long accountNumber);
	
	public List<Customer> getallCustomer();

}



