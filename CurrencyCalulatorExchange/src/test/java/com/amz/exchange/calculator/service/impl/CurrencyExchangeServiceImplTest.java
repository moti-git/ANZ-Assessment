/**
 * 
 */
package com.amz.exchange.calculator.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amz.exchange.calculator.service.CurrencyExchangeService;

/**
 * @author motjha
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class CurrencyExchangeServiceImplTest {
	
	@Autowired
	CurrencyExchangeService currencyExchangeService;

	@Test
	public void CurrencyExchangeServiceAutowired() {
		assertNotNull(currencyExchangeService);
	}
			
	@Test
	public void testCurrencyExchangeSuccess() throws Exception {
		BigDecimal currencyExchnageValue = currencyExchangeService.exchangeCurrency("AUD", "USD", new BigDecimal(100));
	    assertNotNull(currencyExchnageValue);
	}
	
	@Test
	public void testCurrencyExchangeFailure() throws Exception {
		BigDecimal currencyExchnageValue = currencyExchangeService.exchangeCurrency("ABC", "XYZ", new BigDecimal(100));
	    assertNull(currencyExchnageValue);
	}
		
	@Test
	public void testInverseCurrency() throws Exception {
		BigDecimal currencyExchnageValue = currencyExchangeService.exchangeCurrency("USD", "AUD", new BigDecimal(100.00));
	    assertThat(currencyExchnageValue).as("1.19");		
	}
   
	@Test
	public void testCrossCurrency() throws Exception {
		BigDecimal currencyExchnageValue = currencyExchangeService.exchangeCurrency("JPA", "USD", new BigDecimal(100));
	    assertThat(currencyExchnageValue).as("0.83");	
	}
}