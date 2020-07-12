package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.model.Login;
import com.app.model.Response;
import com.app.service.ICustomerService;

@RestController
public class CustomerController {

	@Autowired
	ICustomerService service;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Banking Application!";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Response login(@RequestBody Login login, HttpServletRequest request) {
		Response appResponse = new Response();
		/*if (login != null) {
			if ("admin".equals(login.getUsername()) && "admin".equals(login.getPassword())) {
				Cookie ck = new Cookie("name", login.getUsername());
				response.addCookie(ck);
				appResponse.setMessage("login successfull");
				appResponse.setData(true);
			}
		}else{
			appResponse.setMessage("invalid username and password");
		}*/
		
		HttpSession session = request.getSession(false);
		if (session != null) {
		    // a session exists
		    //user is already logged in 
			session.setAttribute("loggedInUser", login.getUsername());
			appResponse.setMessage("login success");
		} else {
		    // no session create session
			session=request.getSession();
			session.setAttribute("loggedInUser", login.getUsername());
			appResponse.setMessage("login success");
		}
		return appResponse;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
	public Response logout(HttpServletRequest request) {
		Response appResponse = new Response();
	/*	Cookie ck = new Cookie("name", "");
		ck.setMaxAge(0);
		response.addCookie(ck);*/
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
			appResponse.setMessage("logout success");
		}
		return appResponse;
	}

	@RequestMapping(value = "/customer/{accountNumber}", method = RequestMethod.GET, produces = "application/json")
	public Response getCustomer(@PathVariable("accountNumber") long accountNumber, HttpServletRequest request) {
		Response appResponse = new Response();
		/*Cookie ck[] = request.getCookies();
		if (ck != null) {
			String user = ck[0].getValue();
			if ("admin".equals(user)) {
				appResponse.setData(service.getCustomer(accountNumber));
			}
		} else {
			return appResponse;
		}*/
		HttpSession session = request.getSession(false);
		if (session != null) {
		    // a session exists
			List<Customer> list= (List<Customer>) session.getAttribute("customers");
		     if(list!=null){
		    	 for (Customer customer : list) {
					if(customer.getAccountNumber()==accountNumber){
						appResponse.setData(customer);
						appResponse.setMessage("success");
						break ; 
					}
				}
		     }else{
		    	appResponse.setData(null);
				appResponse.setMessage("No record found");
		     }
		} else {
			appResponse.setMessage("Unauthorised request");
			
		}
		return appResponse;
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = "application/json")
	public Response addCustomer(@RequestBody Customer customer, HttpServletRequest request) {
		Response appResponse = new Response();
		HttpSession session = request.getSession(false);
		if (session != null) {
			List<Customer> list= (List<Customer>) session.getAttribute("customers");
			if(list!=null){
		    	 list.add(customer);
		     }else{
		    	list=new ArrayList<Customer>();
		    	list.add(customer);
		     }
			session.setAttribute("customers", list);
			appResponse.setMessage("success");
		}else{
			appResponse.setMessage("Unauthorised request");
		}
		return appResponse;
	}

	@RequestMapping(value = "/customer", method = RequestMethod.PUT, consumes = "application/json")
	public Response updateCustomer(Customer customer, HttpServletRequest request) {
		Response appResponse = new Response();
		HttpSession session = request.getSession(false);
		if (session != null) {
			List<Customer> list= (List<Customer>) session.getAttribute("customers");
			 for (Customer c : list) {
					if(c.getAccountNumber()==customer.getAccountNumber()){
						c.setAccountBalance(customer.getAccountBalance());
						break ; 
					}
				}
			session.setAttribute("customers", list);
			appResponse.setMessage("success");
		}else{
			appResponse.setMessage("Unauthorised request");
		}
		return appResponse;
	}

	@RequestMapping(value = "/customer/{accountNumber}", method = RequestMethod.DELETE, produces = "application/json")
	public Response deleteCustomer(@PathVariable("accountNumber") long accountNumber, HttpServletRequest request) {
		Response appResponse = new Response();
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			List<Customer> list= (List<Customer>) session.getAttribute("customers");
			 for (Customer c : list) {
					if(c.getAccountNumber()==accountNumber){
						list.remove(c);
						break ; 
					}
				}
			session.setAttribute("customers", list);
			appResponse.setMessage("success");
		}else{
			appResponse.setMessage("Unauthorised request");
		}
		return appResponse;
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
	public Response getallCustomer(HttpServletRequest request) {
		Response appResponse = new Response();
		HttpSession session = request.getSession(false);
		if (session != null) {
			List<Customer> list= (List<Customer>) session.getAttribute("customers");
			appResponse.setData(list);
			appResponse.setMessage("success");
		}else{
			appResponse.setMessage("Unauthorised request");
		}
		return appResponse;
		
	}

}
