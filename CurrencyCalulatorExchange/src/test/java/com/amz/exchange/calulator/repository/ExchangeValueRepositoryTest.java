package com.amz.exchange.calulator.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amz.exchange.calculator.CurrencyExchangeCalulatorApplication;
import com.amz.exchange.calculator.model.ExchangeValue;
import com.amz.exchange.calculator.repository.ExchangeValueRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={CurrencyExchangeCalulatorApplication.class})
public class ExchangeValueRepositoryTest {

    @Autowired
    ExchangeValueRepository repository;

    @Test
    public void testFindByFromAndToSuccess() throws Exception {
        ExchangeValue eValue = repository.findByFromAndTo("AUD", "USD");
        assertNotNull(eValue);
    }
    
    @Test
    public void testFindByFromAndToFailure() throws Exception {
        ExchangeValue eValue = repository.findByFromAndTo("XYZ", "INC");
        assertNull(eValue);
    }
}
