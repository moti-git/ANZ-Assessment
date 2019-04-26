/**
 * 
 */
package com.amz.exchange.calculator.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author motjha
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class ExchangeValueRepositoryTest {

	
    @Autowired
    ExchangeValueRepository exchangeValueRepository;
	
    @Test
	public void ExchangeValueRepositoryAutowired() {
		assertNotNull(exchangeValueRepository);
	}
    /**
	 * Test method for {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		assertNotNull(exchangeValueRepository.findAll());
	}

		
}
