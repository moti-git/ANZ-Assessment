package com.amz.exchange.calculator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class CurrencyExchangeDecimalValueTest {

	@Test
	public void testCurrencyExchangeDecimalValueLongStringString() {
		CurrencyExchangeDecimalValue exchValue = new CurrencyExchangeDecimalValue(1001l, "AUD", 2);
		assertEquals(new Long(1001l), exchValue.getId());
		assertSame("AUD", exchValue.getCountryCurrency());
		assertSame(2, exchValue.getDecimalPlace());
			
	}

}
