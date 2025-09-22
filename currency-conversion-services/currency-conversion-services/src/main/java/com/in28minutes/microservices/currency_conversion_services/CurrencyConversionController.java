package com.in28minutes.microservices.currency_conversion_services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController           
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")	
	public CurrencyConversion calculatedCurrencyConversion(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity)
	{		
		return new CurrencyConversion(1001L,from,to,quantity,BigDecimal.ONE,BigDecimal.ONE,"");
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")	
	public CurrencyConversion calculatedCurrencyConversionfeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity)
	{		
		CurrencyConversion currencyConversion=proxy.retriveExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment());
		
	}
}
