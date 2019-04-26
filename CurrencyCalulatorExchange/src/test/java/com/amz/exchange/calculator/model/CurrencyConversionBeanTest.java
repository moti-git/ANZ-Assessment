package com.amz.exchange.calculator.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class CurrencyConversionBeanTest {

	@Test
	public void testCurrencyConversionBeanLongStringStringBigDecimalBigDecimalBigDecimal() {
		CurrencyConversionBean exchValue = new CurrencyConversionBean(1001l, "AUD", "JPY", new BigDecimal(.8371), new BigDecimal(1.00), null);
		exchValue.setTotalCalculatedAmount(exchValue.getConversionMultiple().multiply(exchValue.getQuantity()));
		assertEquals(new Long(1001l), exchValue.getId());
		assertSame("AUD", exchValue.getFrom());
		assertSame("JPY", exchValue.getTo());
		assertEquals(new BigDecimal(.8371), exchValue.getConversionMultiple());
		assertEquals(new BigDecimal(.8371).multiply(new BigDecimal(1)), exchValue.getTotalCalculatedAmount());
	
	}

}
