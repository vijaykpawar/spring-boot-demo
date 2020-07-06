package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.service.ICustomerService;

@RestController
public class CustomerController {

	@Autowired
	ICustomerService service;

	@RequestMapping(value = "/customer/{accountNumber}", method = RequestMethod.GET, produces = "application/json")
	public Customer getCustomer(@PathVariable("accountNumber") long accountNumber) {
		return service.getCustomer(accountNumber);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = "application/json")
	public Customer addCustomer(@RequestBody Customer customer) {
		return service.addCustomer(customer);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.PUT, consumes = "application/json")
	public Customer updateCustomer(Customer customer) {
		return service.updateCustomer(customer);
	}

	@RequestMapping(value = "/customer/{accountNumber}", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteCustomer(@PathVariable("accountNumber") long accountNumber) {
		return service.deleteCustomer(accountNumber);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> getallCustomer() {
		List<Customer> list = service.getallCustomer();
		return list;
	}

}
