/**
 * 
 */
package com.amz.exchange.calculator.strategy.factory;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.amz.exchange.calculator.strategy.impl.CurrencyCrossJoinCalculationStrategyImpl;
import com.amz.exchange.calculator.strategy.impl.CurrencyDirectCalculationStrategyImpl;
import com.amz.exchange.calculator.strategy.impl.CurrencyInverseCalculationStrategyImpl;
import com.amz.exchange.calculator.strategy.impl.CurrencyUnityCalculationStrategyImpl;

/**
 * @author motjha
 *
 */
public class CurrencyCalculationStrategyFactoryTest {

	/**
	 * Test method for {@link com.amz.exchange.calculator.strategy.factory.CurrencyCalculationStrategyFactory#getStrategy(java.lang.Class)}.
	 */
	@Test
	public void testGetUnityStrategy() {
		assertNotNull(CurrencyCalculationStrategyFactory.getStrategy(CurrencyUnityCalculationStrategyImpl.class));
	}
	
	@Test
	public void testGetDirectStrategy() {
		assertNotNull(CurrencyCalculationStrategyFactory.getStrategy(CurrencyDirectCalculationStrategyImpl.class));
	}
	@Test
	public void testGetInverseStrategy() {
		assertNotNull(CurrencyCalculationStrategyFactory.getStrategy(CurrencyInverseCalculationStrategyImpl.class));
	}
	@Test
	public void testGetCrosseStrategy() {
		assertNotNull(CurrencyCalculationStrategyFactory.getStrategy(CurrencyCrossJoinCalculationStrategyImpl.class));
	}

}
