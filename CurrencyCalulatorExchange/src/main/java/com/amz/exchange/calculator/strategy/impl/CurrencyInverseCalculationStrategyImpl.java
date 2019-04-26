package com.amz.exchange.calculator.strategy.impl;

import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.ExchangeValue;
import com.amz.exchange.calculator.strategy.CurrencyCalculationStrategy;

public class CurrencyInverseCalculationStrategyImpl implements CurrencyCalculationStrategy {

	
	@Override
	public CurrencyConversionBean convertCurrency(CurrencyConversionBean currencyConversionBean,
			String currencyMaterixValue, Map<String, ExchangeValue> currencyExchangeMap, List<ExchangeValue> exchangeValueList) throws Exception {
		
		ExchangeValue currencyValue = currencyExchangeMap
				.get(currencyConversionBean.getFrom()+currencyConversionBean.getTo());
		currencyConversionBean.setConversionMultiple(currencyValue.getConversionMultiple());

		// Inverse currency exchange calculation
		currencyConversionBean.setTotalCalculatedAmount(
				currencyConversionBean.getConversionMultiple().multiply(currencyConversionBean.getQuantity()).setScale(4, RoundingMode.DOWN));

		return currencyConversionBean;
	}

}
