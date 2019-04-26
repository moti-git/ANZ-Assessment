/**
 * 
 */
package com.amz.exchange.calculator.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * @author motjha
 *
 */
public class ExchangeValueTest {

	/**
	 * Test method for {@link com.amz.exchange.calculator.model.ExchangeValue#ExchangeValue(java.lang.Long, java.lang.String, java.lang.String, java.math.BigDecimal)}.
	 */
	@Test
	public void testExchangeValueLongStringStringBigDecimal() {
		ExchangeValue exchValue = new ExchangeValue(1001l, "AUD", "JPY", new BigDecimal(.8371));
		assertEquals(new Long(1001l), exchValue.getId());
		assertSame("AUD", exchValue.getFrom());
		assertSame("JPY", exchValue.getTo());
		assertEquals(new BigDecimal(.8371), exchValue.getConversionMultiple());
				
	}

}
