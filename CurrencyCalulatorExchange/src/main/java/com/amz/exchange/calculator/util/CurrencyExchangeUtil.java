package com.amz.exchange.calculator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.CurrencyExchangeDecimalValue;
import com.amz.exchange.calculator.model.ExchangeValue;

public class CurrencyExchangeUtil {
	
	/**
	 * @param currencyConversionBean
	 * @param materix
	 */
	public static String getMaterixCurrencyMapValue(CurrencyConversionBean currencyConversionBean, String[][] materix) {
		String currencyFromValue = currencyConversionBean.getFrom();
	     String currencyToValue = currencyConversionBean.getTo();
	     int rowIndex = 0;
	     int columIndex = 0;
	     boolean rowFound = false;
	     boolean columnFound = false;
	     
	     for (int row = 1; row < materix.length; row++) {
				String currencyBaseValue = materix[row][0];
				String currencyTargetValue = materix[0][row];
				if(!rowFound && currencyBaseValue.equals(currencyFromValue)) {
					rowIndex = row;
					rowFound = true;
				}
				
				if(!columnFound && currencyTargetValue.equals(currencyToValue)) {
					columIndex = row;
					columnFound = true;
				}
				if (rowFound && columnFound) {
					break;
				}				
				
	     }	
	     
	      return materix[rowIndex][columIndex];
	}	

	public static String findCrossCurrencyValue(List<ExchangeValue> exchangeValueList, String currencyFromValue,
			String currencytoValue, boolean repeatFlag) {
   
		String crossCurrencyValue = "USD";
		String commonCurrencyValue = null;
		
		
		List<ExchangeValue> exchangeFromValueList = new ArrayList<ExchangeValue>();
		List<ExchangeValue>  exchangetoValueList = new ArrayList<ExchangeValue>();
		
		//Get all from currency mapped list
		exchangeFromValueList = exchangeValueList.stream()
				.filter(exchanValue -> currencyFromValue.equals(exchanValue.getFrom())
						|| currencyFromValue.equals(exchanValue.getTo())).collect(Collectors.toList());
		
		//Get all to currency mapped list
		exchangetoValueList = exchangeValueList.stream()
				.filter(exchanValue -> currencytoValue.equals(exchanValue.getFrom())
						|| currencytoValue.equals(exchanValue.getTo())).collect(Collectors.toList());
		
		// find the matching currency
		if (exchangetoValueList.size() > exchangeFromValueList.size()) {
			for (ExchangeValue fromCurrency : exchangeFromValueList) {
				commonCurrencyValue = fromCurrency.getFrom().equals(currencyFromValue) ? fromCurrency.getTo()
						: fromCurrency.getTo();
				for (ExchangeValue toCurrency : exchangetoValueList) {
                     if(toCurrency.getFrom().equals(commonCurrencyValue) || toCurrency.getTo().equals(commonCurrencyValue)) {
                    	 crossCurrencyValue = toCurrency.getFrom().equals(commonCurrencyValue) ? toCurrency.getFrom()
							: toCurrency.getTo();
                    	 break;
                     }
				}
			}

		} else {
			for (ExchangeValue toCurrency : exchangetoValueList) {
				commonCurrencyValue = toCurrency.getTo().equals(currencytoValue) ? toCurrency.getFrom()
						: toCurrency.getTo();
				for (ExchangeValue fromCurrency : exchangeFromValueList) {
					 if(fromCurrency.getFrom().equals(commonCurrencyValue) || fromCurrency.getTo().equals(commonCurrencyValue)) {
					crossCurrencyValue = fromCurrency.getFrom().equals(commonCurrencyValue) ? fromCurrency.getFrom()
							: fromCurrency.getTo();
					  break;
					 }

				}
			}
		}

		return crossCurrencyValue;
	}

	public static int getCurrencyDecimalPlace(List<CurrencyExchangeDecimalValue> currencyDecimalValueList, String toCurrency) {
		int decimalValue = 0;
		CurrencyExchangeDecimalValue curreDecimalValue = currencyDecimalValueList.stream()
				.filter(curreDecimalVal -> curreDecimalVal.getCountryCurrency().equals(toCurrency))
				.findAny().orElse(null);
		
		if(null != curreDecimalValue) {
			return curreDecimalValue.getDecimalPlace();
		}
		
		return decimalValue;
	}
}
