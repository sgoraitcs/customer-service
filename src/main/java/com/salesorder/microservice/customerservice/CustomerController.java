package com.salesorder.microservice.customerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
		
	}
	
	@PostMapping("/customer")
	public Long createOrder(@RequestBody Customer  customer){
		List<Customer> list = getAllCustomers();
		long id =0;
		for(Customer c : list) {
			if(id < c.getId()) {
				id=c.getId();
			}
		}
		id++;
		customer.setId(id);
		customerRepository.save(customer);
		return id;
		
	}
	

}
