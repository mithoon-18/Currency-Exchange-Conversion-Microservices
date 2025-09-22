package com.in28minutes.microservices.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.retry.annotation.Retry;
@RestController
public class CircuitBreakerController {
	
	private Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("sample-api")
	@Retry(name="sample-api", fallbackMethod="hardcodedresponse")
	public int sampleApi() {
		logger.info("sample api call received");
		
		int a=2;
		int c=0;
		if(a==2)
		{
			c=a/0;
		}
		return c;
//		return "sample Api";
	}
	
	public String hardcodedresponse(Exception ex)
	{
		return "fallback-response";
	}
}
