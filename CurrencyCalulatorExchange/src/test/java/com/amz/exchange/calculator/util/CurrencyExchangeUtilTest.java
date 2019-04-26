/**
 * 
 */
package com.amz.exchange.calculator.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.CurrencyExchangeDecimalValue;
import com.amz.exchange.calculator.model.ExchangeValue;

/**
 * @author motjha
 *
 */
public class CurrencyExchangeUtilTest {
	
	/**
	 * Test method for {@link com.amz.exchange.calculator.util.CurrencyExchangeUtil#getMaterixCurrencyMapValue(com.amz.exchange.calculator.model.CurrencyConversionBean, java.lang.String[][])}.
	 */
	@Test
	public void testGetMaterixCurrencyMapValue() {
		
		String [][]materix = { { "","AUD","CAD", "CNY", "CZK", "DKK", "EUR","GBP", "JPY","NOK","NZD", "USD" },
				{ "AUD","1:1","USD", "USD", "USD", "USD", "USD","USD", "USD","USD","USD", "D" },
				{ "CAD","USD","1:1", "USD", "USD", "USD", "USD","USD", "USD","USD","USD", "D" },
				{ "CNY","USD","USD", "1:1", "USD", "USD", "USD","USD", "USD","USD","USD", "D" },
				{ "CZK","USD","USD", "USD", "1:1", "EUR", "Inv","USD", "USD","EUR","USD", "EUR" },
				{ "DKK","USD","USD", "USD", "EUR", "1:1", "Inv","USD", "USD","EUR","USD", "EUR" },
				{ "EUR","USD","USD", "CNY", "D", "D", "1:1","USD", "USD","D","USD", "D" },
				{ "GBP","USD","USD", "USD", "USD", "USD", "USD","1:1", "USD","USD","USD", "D" },
				{ "JPY","USD","USD", "USD", "USD", "USD", "USD","USD", "1:1","USD","USD", "Inv" },
				{ "NOK","USD","USD", "USD", "EUR", "EUR", "Inv","USD", "USD","1:1","USD", "EUR" },
				{ "NZD","USD","USD", "USD", "USD", "USD", "USD","USD", "USD","USD","I:1", "USD" },
				{ "USD","Inv","Inv", "Inv", "EUR", "EUR", "Inv","Inv", "D","EUR","Inv", "1:1" }}; 
		
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(null, "AUD", "JPY", null, new BigDecimal(1), null);
		assertEquals("USD", CurrencyExchangeUtil.getMaterixCurrencyMapValue(currencyConversionBean, materix)); 
	}

	/**
	 * Test method for {@link com.amz.exchange.calculator.util.CurrencyExchangeUtil#findCrossCurrencyValue(java.util.List, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testFindCrossCurrencyValue() {
     		
		List<ExchangeValue> exchangeTestValueList = new ArrayList<>();	
		
		ExchangeValue e1 = new ExchangeValue(1001l, "AUD", "USD", new BigDecimal(0.8371));
		ExchangeValue e2 = new ExchangeValue(1001l, "CAD", "USD", new BigDecimal(0.8711));
		ExchangeValue e3 = new ExchangeValue(1001l, "USD", "JPA", new BigDecimal(119.95));
				
		exchangeTestValueList.add(e1);
		exchangeTestValueList.add(e2);
		exchangeTestValueList.add(e3);
		
		assertEquals("USD", CurrencyExchangeUtil.findCrossCurrencyValue(exchangeTestValueList, "AUD", "JPY", false));
	}
	
	@Test
	public void testCurrencyDecimalPlaceSuccess() {
		
       List<CurrencyExchangeDecimalValue> currencyDecmialValueList = new ArrayList<>();	
		
		CurrencyExchangeDecimalValue e1 = new CurrencyExchangeDecimalValue(1001l, "AUD", 2);
		CurrencyExchangeDecimalValue e2 = new CurrencyExchangeDecimalValue(1001l, "USD", 2);
		CurrencyExchangeDecimalValue e3 = new CurrencyExchangeDecimalValue(1001l, "JPY", 0);
		currencyDecmialValueList.add(e1);
		currencyDecmialValueList.add(e2);
		currencyDecmialValueList.add(e3);
		
		assertEquals(2, CurrencyExchangeUtil.getCurrencyDecimalPlace(currencyDecmialValueList, "AUD"));
		
	}
	
	@Test
	public void testCurrencyDecimalPlaceFailure() {
		
       List<CurrencyExchangeDecimalValue> currencyDecmialValueList = new ArrayList<>();	
		
		CurrencyExchangeDecimalValue e1 = new CurrencyExchangeDecimalValue(1001l, "AUD", 2);
		CurrencyExchangeDecimalValue e2 = new CurrencyExchangeDecimalValue(1001l, "USD", 2);
		CurrencyExchangeDecimalValue e3 = new CurrencyExchangeDecimalValue(1001l, "JPY", 0);
		currencyDecmialValueList.add(e1);
		currencyDecmialValueList.add(e2);
		currencyDecmialValueList.add(e3);
		
		assertEquals(0, CurrencyExchangeUtil.getCurrencyDecimalPlace(currencyDecmialValueList, "ABC"));
		
	}

}
