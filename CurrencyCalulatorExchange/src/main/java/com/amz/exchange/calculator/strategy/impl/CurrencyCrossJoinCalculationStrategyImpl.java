package com.amz.exchange.calculator.strategy.impl;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.ExchangeValue;
import com.amz.exchange.calculator.strategy.CurrencyCalculationStrategy;
import com.amz.exchange.calculator.util.CurrencyExchangeUtil;

public class CurrencyCrossJoinCalculationStrategyImpl implements CurrencyCalculationStrategy {

	@Override
	public CurrencyConversionBean convertCurrency(CurrencyConversionBean currencyConversionBean, String materixMapValue,
			Map<String, ExchangeValue> currencyExchangeMap, List<ExchangeValue> exchangeValueList) throws Exception {

		String crossCurrency = null;
		ExchangeValue  fromCurrencyValue = currencyExchangeMap.get(currencyConversionBean.getFrom() + materixMapValue);
				
		if(null == fromCurrencyValue) {
			return currencyConversionBean;
		}
		currencyConversionBean.setConversionMultiple(fromCurrencyValue.getConversionMultiple());
		ExchangeValue crossCurrencyValue = currencyExchangeMap.get(materixMapValue + currencyConversionBean.getTo());
		if (null == crossCurrencyValue) {
			crossCurrency = CurrencyExchangeUtil.findCrossCurrencyValue(exchangeValueList, materixMapValue,
					currencyConversionBean.getTo(), false);

			if (crossCurrency != null && !crossCurrency.equals(materixMapValue)) {
				currencyConversionBean.setConversionMultiple(currencyConversionBean.getConversionMultiple()
						.multiply(currencyExchangeMap.get(materixMapValue + crossCurrency).getConversionMultiple())
						.multiply(currencyExchangeMap.get(crossCurrency + currencyConversionBean.getTo())
								.getConversionMultiple()));
			}
		} else {
			currencyConversionBean.setConversionMultiple(
					currencyConversionBean.getConversionMultiple().multiply(crossCurrencyValue.getConversionMultiple()));
		}

		// Cross currency exchange
		currencyConversionBean.setTotalCalculatedAmount(currencyConversionBean.getConversionMultiple()
				.multiply(currencyConversionBean.getQuantity()).setScale(4, RoundingMode.DOWN));

		return currencyConversionBean;
	}

}
