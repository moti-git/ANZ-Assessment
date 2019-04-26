package com.amz.exchange.calculator.service;

import java.math.BigDecimal;

//@Service
public interface CurrencyExchangeService {
	
   public BigDecimal exchangeCurrency(String from, String to, BigDecimal amount) throws Exception;
}
