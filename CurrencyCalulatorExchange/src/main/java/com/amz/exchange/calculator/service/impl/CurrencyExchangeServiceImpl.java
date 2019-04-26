/**
 * 
 */
package com.amz.exchange.calculator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amz.exchange.calculator.handler.CurrencyExchangeHandler;
import com.amz.exchange.calculator.handler.CurrencyExchangeHandlerResponsibilty;
import com.amz.exchange.calculator.model.CurrencyConversionBean;
import com.amz.exchange.calculator.model.CurrencyExchangeDecimalValue;
import com.amz.exchange.calculator.model.ExchangeValue;
import com.amz.exchange.calculator.repository.CurrencyDecimalValueRepository;
import com.amz.exchange.calculator.repository.ExchangeValueRepository;
import com.amz.exchange.calculator.service.CurrencyExchangeService;
import com.amz.exchange.calculator.util.CurrencyExchangeUtil;

/**
 * @author motjha
 *
 */
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

	private static final Log logger = LogFactory.getLog(CurrencyExchangeServiceImpl.class);

	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	@Autowired
	private CurrencyDecimalValueRepository currencyDecimalValueRepository;

	public static Map<String, ExchangeValue> currencyExchangeDirectMap = new HashMap<>();

	public static Map<String, ExchangeValue> currencyExchangeDirectAndInverseMap = new HashMap<>();

	public static List<ExchangeValue> exchangeValueList = new ArrayList<>();

	public static List<CurrencyExchangeDecimalValue> currencyDecimalValueList = new ArrayList<>();

	public static String[][] materix = null;

	@PostConstruct
	public void init() {
		
		logger.info("Inside init to load all currency mapping data...");
		
		// Get available currency details from DB
		exchangeValueList = exchangeValueRepository.findAll();

		currencyExchangeDirectMap = exchangeValueList.stream().collect(HashMap::new,
				(m1, v1) -> m1.put(v1.getFrom() + v1.getTo(), v1), HashMap::putAll);

		exchangeValueList.forEach((v2) -> {

			currencyExchangeDirectAndInverseMap.put(v2.getTo() + v2.getFrom(),
					new ExchangeValue(v2.getId(), v2.getFrom(), v2.getTo(),
							new BigDecimal(1).divide(v2.getConversionMultiple(), 4, RoundingMode.DOWN)));
		});

		currencyExchangeDirectAndInverseMap.putAll(currencyExchangeDirectMap);

		currencyDecimalValueList = currencyDecimalValueRepository.findAll();

		// Initialize matrix to keep map details.
		materix = new String[currencyDecimalValueList.size() + 1][currencyDecimalValueList.size() + 1];

		for (int i = 0; i < currencyDecimalValueList.size(); i++) {
			materix[0][1 + i] = currencyDecimalValueList.get(i).getCountryCurrency();
			materix[1 + i][0] = currencyDecimalValueList.get(i).getCountryCurrency();
		}

		for (int row = 1; row < materix.length; row++) {
			String currencyFromValue = materix[row][0];

			for (int column = 1; column < materix[row].length; column++) {
				String currencyToValue = materix[0][column];
				if (row == column) {
					materix[row][column] = "1:1";
				} else if (null != currencyExchangeDirectMap.get(currencyFromValue + currencyToValue)) {
					materix[row][column] = "D";
				} else if (null != currencyExchangeDirectMap.get(currencyToValue + currencyFromValue)) {
					materix[row][column] = "Inv";
				} else {
					materix[row][column] = CurrencyExchangeUtil.findCrossCurrencyValue(exchangeValueList,
							currencyFromValue, currencyToValue, false);
				}

			}

		}
		
		logger.info("Initialization completed...");
	}

	@Override
	public BigDecimal exchangeCurrency(String from, String to, BigDecimal amount) throws Exception {

		logger.info("CurrencyExchangeServiceImpl::exchangeCurrency(): start ");
		
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(null, from, to, null, amount, null);

		// To get chain handlers to exchange currency
		CurrencyExchangeHandler cuExchangeHandler = CurrencyExchangeHandlerResponsibilty
				.getChainOfCurrencyExchangeHandler();

		// Handler will decide which calculation will apply based currency mapping.
		cuExchangeHandler.convertCurrency(currencyConversionBean, materix, currencyExchangeDirectAndInverseMap,
				exchangeValueList);
		logger.info("CurrencyExchangeServiceImpl::exchangeCurrency(): end with caculated value "+currencyConversionBean.getTotalCalculatedAmount());
		
		if (null == currencyConversionBean.getTotalCalculatedAmount()) {
			return null;
		}
		return currencyConversionBean.getTotalCalculatedAmount().setScale(
				CurrencyExchangeUtil.getCurrencyDecimalPlace(currencyDecimalValueList, to), RoundingMode.DOWN);
	}

}
