/**
 * 
 */
package com.amz.exchange.calculator.handler;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author motjha
 *
 */
public class CurrencyExchangeHandlerResponsibiltyTest {

	 @Autowired
	 CurrencyExchangeHandlerResponsibilty currencyExchangeHandlerResponsibilty;
	/**
	 * Test method for {@link com.amz.exchange.calculator.handler.CurrencyExchangeHandlerResponsibilty#getChainOfCurrencyExchangeHandler()}.
	 */
	@Test
	public void testGetChainOfCurrencyExchangeHandler() {
		assertNotNull(CurrencyExchangeHandlerResponsibilty.getChainOfCurrencyExchangeHandler());
	}

}
