package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}


@RestController
public class CurrencyConversionController {
	
	Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		
		logger.info("from:"+from+",to:"+to+",quantity:"+quantity);

		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity
		("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, uriVariables);
		
		CurrencyConversion currencyConversion = responseEntity.getBody();
		
		logger.info("currencyConversion:"+currencyConversion);
		
		currencyConversion.setQuantity(quantity);
		BigDecimal conversionMultiple = currencyConversion.getConversionMultiple();
		
		BigDecimal totalCalculateAmount = quantity.multiply(conversionMultiple);
				
		currencyConversion.setTotalCalculatedAmount(totalCalculateAmount);
		logger.info("after  setting calculated amt, currencyConversion:"+currencyConversion);
		
		return currencyConversion;
	
		
	}

	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
				
		logger.info("currencyconversionfeign:from:"+from+",to:"+to+",quantity:"+quantity);
		
		
		CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
		
        logger.info("currencyConversionfeign:"+currencyConversion);
		
		currencyConversion.setQuantity(quantity);
		BigDecimal conversionMultiple = currencyConversion.getConversionMultiple();
		
		BigDecimal totalCalculateAmount = quantity.multiply(conversionMultiple);
				
		currencyConversion.setTotalCalculatedAmount(totalCalculateAmount);
		logger.info("after  setting calculated amt, currencyConversionfeign:"+currencyConversion);
		
		return currencyConversion;
	
	}

   
}
