package com.amz.exchange.calculator.handler.impl;

import java.util.List;
import java.util.Map;

import com.amz.exchange.calculator.handler.CurrencyExchangeHandler;
import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.ExchangeValue;
import com.amz.exchange.calculator.strategy.CurrencyCalculationStrategy;
import com.amz.exchange.calculator.strategy.factory.CurrencyCalculationStrategyFactory;
import com.amz.exchange.calculator.strategy.impl.CurrencyDirectCalculationStrategyImpl;
import com.amz.exchange.calculator.util.CurrencyExchangeUtil;


public class CurrencyExchangeDirectCalculationHandler implements CurrencyExchangeHandler {
   
	private CurrencyExchangeHandler chain;
 

	@Override
	public void setNextCurrencyHandler(CurrencyExchangeHandler nextChain) {
		this.chain = nextChain;
		
	}

	@Override
	public CurrencyConversionBean convertCurrency(CurrencyConversionBean currencyConversionBean, String[][] materix,
			Map<String, ExchangeValue> currencyExchangeMap, List<ExchangeValue> exchangeValueList) throws Exception {
		
		  String materixMapValue = CurrencyExchangeUtil.getMaterixCurrencyMapValue(currencyConversionBean, materix);
		  if (materixMapValue == "D" ) {
			  CurrencyCalculationStrategy calulationStrategy = CurrencyCalculationStrategyFactory.getStrategy(CurrencyDirectCalculationStrategyImpl.class);
			 return calulationStrategy.convertCurrency(currencyConversionBean, materixMapValue, currencyExchangeMap, exchangeValueList);
		  }
	        return callNextChain(currencyConversionBean, materix, currencyExchangeMap, exchangeValueList);
	}
	

	private CurrencyConversionBean callNextChain(CurrencyConversionBean currencyConversionBean, String[][] materix,
			Map<String, ExchangeValue> currencyExchangeMap, List<ExchangeValue> exchangeValueList) throws Exception {

		if (null == this.chain) {
			return currencyConversionBean;
		}

		return this.chain.convertCurrency(currencyConversionBean, materix, currencyExchangeMap, exchangeValueList);
	}

}
