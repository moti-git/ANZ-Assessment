package com.amz.exchange.calculator.controller;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amz.exchange.calculator.service.CurrencyExchangeService;

/**
 * @author motjha
 *
 */
@RestController
public class CurrencyExchangeCalulatorController {

	private static final Log logger = LogFactory.getLog(CurrencyExchangeCalulatorController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	CurrencyExchangeService currencyExchangeService;

	/*
	 * API to convert one country currency value to another.
	 * 
	 */

	@GetMapping("/convertcurrency/{from}/{to}/{amount}")
	public ResponseEntity<?> convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable String amount) throws Exception {

		logger.info("CurrencyExchangeCalulatorController::convertCurrency():start");
		BigDecimal calculatedExchangeAmount = null;

		if (StringUtils.isEmpty(from) && StringUtils.isEmpty(to) && StringUtils.isEmpty(amount)) {
			logger.error("Invalid Input Error occurred ");
			throw new Exception("Invalid Input");
		} else {
			calculatedExchangeAmount = currencyExchangeService.exchangeCurrency(from, to, new BigDecimal(amount));
			logger.info("CurrencyExchangeCalulatorController::convertCurrency(): "+from+" to "+to+ " "+ calculatedExchangeAmount);

		}
		return new ResponseEntity<>(calculatedExchangeAmount, HttpStatus.OK);

	}

}
