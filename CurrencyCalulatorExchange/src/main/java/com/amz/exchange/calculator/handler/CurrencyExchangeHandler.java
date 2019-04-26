package com.amz.exchange.calculator.handler;


import java.util.List;
import java.util.Map;

import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.ExchangeValue;

public interface CurrencyExchangeHandler {
    
	public void setNextCurrencyHandler(CurrencyExchangeHandler nextChain);

	public CurrencyConversionBean convertCurrency(CurrencyConversionBean currencyConversionBean, String[][] materix,
			Map<String, ExchangeValue> currencyExchangeMap, List<ExchangeValue> exchangeValueList) throws Exception;
}
		