package com.amz.exchange.calculator.strategy;

import java.util.List;
import java.util.Map;

import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.ExchangeValue;

public interface CurrencyCalculationStrategy {

	public CurrencyConversionBean convertCurrency(CurrencyConversionBean currencyConversionBean,
			String currencyMaterixValue, Map<String, ExchangeValue> currencyExchangeMap, List<ExchangeValue> exchangeValueList) throws Exception;

}
