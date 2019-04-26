package com.amz.exchange.calulator.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.amz.exchange.calculator.CurrencyExchangeCalulatorApplication;
import com.amz.exchange.calculator.controller.CurrencyExchangeCalulatorController;
import com.amz.exchange.calculator.service.CurrencyExchangeService;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyExchangeCalulatorController.class)
@ContextConfiguration(classes = { CurrencyExchangeCalulatorApplication.class })
public class CurrencyExchangeCaculatorControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CurrencyExchangeService currencyExchangeService;

	@Autowired
	private CurrencyExchangeCalulatorController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testConvertCurrencyServiceStatus() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/convertcurrency/AUD/USD/345678")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		
	}

	@Test
	public void testSameConvertCurrency() throws Exception {

		assertThat(MockMvcRequestBuilders.get("/convertcurrency/AUD/AUD/100.00", BigDecimal.class)).as("100.00");

	}

	@Test
	public void testDirectConvertCurrency() throws Exception {
		assertThat(MockMvcRequestBuilders.get("http://localhost:8282/currencyexchangeconvertcurrency/AUD/USD/100.00",
				BigDecimal.class)).as("83.71");
	}
	
	@Test
	public void testInverseCurrency() throws Exception {
		assertThat(MockMvcRequestBuilders.get("/convertcurrency/USD/AUD/100.00",
				BigDecimal.class)).as("1.19");
	}
   
	@Test
	public void testCrossCurrency() throws Exception {
		assertThat(MockMvcRequestBuilders.get("/convertcurrency/JPY/USD/100",
				BigDecimal.class)).as("0.83");
	}
}
