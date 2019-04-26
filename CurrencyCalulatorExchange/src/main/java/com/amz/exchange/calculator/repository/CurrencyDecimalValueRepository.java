package com.amz.exchange.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amz.exchange.calculator.model.CurrencyExchangeDecimalValue;

@Repository	
public interface CurrencyDecimalValueRepository extends 
    JpaRepository<CurrencyExchangeDecimalValue, Long>{
	
  
   
   	 
}
