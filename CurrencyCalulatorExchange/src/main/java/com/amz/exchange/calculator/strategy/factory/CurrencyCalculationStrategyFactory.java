package com.amz.exchange.calculator.strategy.factory;

import com.amz.exchange.calculator.strategy.CurrencyCalculationStrategy;

public class CurrencyCalculationStrategyFactory {

    public static CurrencyCalculationStrategy getStrategy(Class<?> clazz) {

    	CurrencyCalculationStrategy strategy = null;
        try {
            strategy = (CurrencyCalculationStrategy) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to create DiscountStrategy", e);
        }

        return strategy;
    }

}
