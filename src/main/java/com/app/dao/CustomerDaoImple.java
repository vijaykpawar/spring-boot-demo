package com.app.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Customer;

@Repository
public class CustomerDaoImple implements ICustomerDao {

	@Autowired
	private HttpSession httpSession;

	public static final Map<Long, Customer> custMap = new HashMap<Long, Customer>();

	static {
		initCustomers();
	}

	private static void initCustomers() {

		Customer cust1 = new Customer(100, 9876);
		Customer cust2 = new Customer(101, 5678);
		Customer cust3 = new Customer(102, 7868);
		Customer cust4 = new Customer(103, 2838);
		Customer cust5 = new Customer(104, 9898);

		custMap.put(cust1.getAccountNumber(), cust1);
		custMap.put(cust2.getAccountNumber(), cust2);
		custMap.put(cust3.getAccountNumber(), cust3);
		custMap.put(cust4.getAccountNumber(), cust4);
		custMap.put(cust5.getAccountNumber(), cust5);

	}

	@Override
	public Customer getCustomer(long accountNumber) {
		return custMap.get(accountNumber);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		custMap.put(customer.getAccountNumber(), customer);
		return customer;

	}

	@Override
	public Customer updateCustomer(Customer customer) {
		custMap.put(customer.getAccountNumber(), customer);
		return customer;
	}

	@Override
	public String deleteCustomer(long accountNumber) {
		custMap.remove(accountNumber);
		return "Customer deleted with account number" +accountNumber;
	}

	@Override
	public List<Customer> getallCustomer() {
		Collection<Customer> c = custMap.values();
		List<Customer> list = new ArrayList<Customer>();
		list.addAll(c);
		return list;
	}

}
