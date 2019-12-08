package com.salesorder.microservice.customerservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="zuul-edge-server")
@RibbonClient(name ="sales-order-service")
public interface SalesOrderService {
	
	@PostMapping("/sales-order-service/customer")
	public boolean createCustomerByEvent(@RequestBody CustomerSOS  customer);

}
