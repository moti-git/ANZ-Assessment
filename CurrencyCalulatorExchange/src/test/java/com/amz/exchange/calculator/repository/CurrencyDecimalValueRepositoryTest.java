package com.amz.exchange.calculator.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class CurrencyDecimalValueRepositoryTest {

		
	@Autowired
	CurrencyDecimalValueRepository currencyDecimalValueRepository;
	
    @Test
	public void ExchangeValueRepositoryAutowired() {
		assertNotNull(currencyDecimalValueRepository);
	}
    /**
	 * Test method for {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		assertNotNull(currencyDecimalValueRepository.findAll());
	}


}
