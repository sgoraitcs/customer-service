package com.salesorder.microservice.customerservice;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class CustomerService {

	private final RabbitTemplate rabbitTemplate;

	  private final Exchange exchange;

	  public CustomerService(RabbitTemplate rabbitTemplate, Exchange exchange) {
	    this.rabbitTemplate = rabbitTemplate;
	    this.exchange = exchange;
	  }

	  public void createCustomer(Customer customer) {
	    String routingKey = "CustomerCreated";
	    //String message = "customer created";
	    CustomerSOS sos = new CustomerSOS(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
	    rabbitTemplate.convertAndSend(exchange.getName(), routingKey, sos);
	  }

}
