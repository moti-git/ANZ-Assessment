package com.amz.exchange.calculator.handler.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amz.exchange.calculator.controller.CurrencyExchangeCalulatorController;
import com.amz.exchange.calculator.handler.CurrencyExchangeHandler;
import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.ExchangeValue;
import com.amz.exchange.calculator.strategy.CurrencyCalculationStrategy;
import com.amz.exchange.calculator.strategy.factory.CurrencyCalculationStrategyFactory;
import com.amz.exchange.calculator.strategy.impl.CurrencyCrossJoinCalculationStrategyImpl;
import com.amz.exchange.calculator.util.CurrencyExchangeUtil;


public class CurrencyExchangeCrossCalculationHandler implements CurrencyExchangeHandler {
	
	private static final Log logger = LogFactory.getLog(CurrencyExchangeCrossCalculationHandler.class);
   
	private CurrencyExchangeHandler chain;
 

	@Override
	public void setNextCurrencyHandler(CurrencyExchangeHandler nextChain) {
		this.chain = nextChain;
		
	}

	@Override
	public CurrencyConversionBean convertCurrency(CurrencyConversionBean currencyConversionBean, String[][] materix,
			Map<String, ExchangeValue> currencyExchangeMap, List<ExchangeValue> exchangeValueList) throws Exception {
		
		logger.info("CurrencyExchangeCrossCalculationHandler::convertCurrency(): start");
		  String materixMapValue = CurrencyExchangeUtil.getMaterixCurrencyMapValue(currencyConversionBean, materix);
		  if (materixMapValue != "1:1" && materixMapValue != "D" &&
				  materixMapValue != "Inv") {
			  CurrencyCalculationStrategy calulationStrategy = CurrencyCalculationStrategyFactory.getStrategy(CurrencyCrossJoinCalculationStrategyImpl.class);
			  return calulationStrategy.convertCurrency(currencyConversionBean, materixMapValue, currencyExchangeMap,exchangeValueList);
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
