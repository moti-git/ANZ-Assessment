package com.amz.exchange.calculator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrencyExchangeDecimalValue {

	@Id
	private Long id;

	@Column(name = "country_currency")
	private String countryCurrency;

	@Column(name = "decimal_place")
	private int decimalPlace;

	public CurrencyExchangeDecimalValue() {

	}

	public CurrencyExchangeDecimalValue(Long id, String countryCurrency, int decimalPlace) {
		super();
		this.id = id;
		this.countryCurrency = countryCurrency;
		this.decimalPlace = decimalPlace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCurrency() {
		return countryCurrency;
	}

	public void setCountryCurrency(String countryCurrency) {
		this.countryCurrency = countryCurrency;
	}

	public int getDecimalPlace() {
		return decimalPlace;
	}

	public void setDecimalPlace(int decimalPlace) {
		this.decimalPlace = decimalPlace;
	}

}