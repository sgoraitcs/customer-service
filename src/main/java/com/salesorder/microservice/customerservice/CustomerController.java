package com.salesorder.microservice.customerservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomerController {
	
	private static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		log.info("Inside getAllCustomers");		
		return customerRepository.findAll();
		
	}
	
	@PostMapping("/customer")
	public Long createCustomer(@RequestBody Customer  customer){
		log.info("Inside createCustomer");		
		List<Customer> list = getAllCustomers();
		long id = generateId(list);
		customer.setId(id);
		customerRepository.save(customer);
		salesOrderService.createCustomerByEvent(new CustomerSOS(id, customer.getFirstName(), customer.getLastName(), customer.getEmail()));
		return id;
		
	}

	private long generateId(List<Customer> list) {
		long id =0;
		for(Customer c : list) {
			if(id < c.getId()) {
				id=c.getId();
			}
		}
		return id+1;
	}
	

}
