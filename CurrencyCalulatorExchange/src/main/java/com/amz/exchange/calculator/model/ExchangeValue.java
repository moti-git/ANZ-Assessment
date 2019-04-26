package com.amz.exchange.calculator.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {
  
  @Id
  private Long id;
  @Column(name="currency_from")
  private String from;
  
  @Column(name="currency_to")
  private String to;
  
  @Column(name="conversion_multiple", precision=21, scale = 4, nullable = false)
  private BigDecimal conversionMultiple;
 
  
	public void setId(Long id) {
		this.id = id;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public ExchangeValue() {

	}

	public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}

	public Long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

}