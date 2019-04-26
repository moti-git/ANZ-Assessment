package com.amz.exchange.calculator.handler;

import com.amz.exchange.calculator.handler.impl.CurrencyExchangeCrossCalculationHandler;
import com.amz.exchange.calculator.handler.impl.CurrencyExchangeDirectCalculationHandler;
import com.amz.exchange.calculator.handler.impl.CurrencyExchangeInverseCalculationHandler;
import com.amz.exchange.calculator.handler.impl.CurrencyExchangeUnityCalculationHandler;

public class CurrencyExchangeHandlerResponsibilty {
	
	private CurrencyExchangeHandlerResponsibilty() {

    }	
	
    public static CurrencyExchangeHandler getChainOfCurrencyExchangeHandler() {
    	
    	//Creating chain of responsibility to calculate     
        CurrencyExchangeHandler currecnyHandler1 = new CurrencyExchangeUnityCalculationHandler();
        
        CurrencyExchangeHandler currecnyHandler2 = new CurrencyExchangeDirectCalculationHandler();
        currecnyHandler1.setNextCurrencyHandler(currecnyHandler2);
        
        CurrencyExchangeHandler currecnyHandler3 = new CurrencyExchangeInverseCalculationHandler();
    	currecnyHandler2.setNextCurrencyHandler(currecnyHandler3);
    	
    	CurrencyExchangeHandler currecnyHandler4 = new CurrencyExchangeCrossCalculationHandler();
    	currecnyHandler3.setNextCurrencyHandler(currecnyHandler4);

        return currecnyHandler1;
    }

}
