package org.sai.play.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sai.play.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Matchers.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


/**
 * Created by IntelliJ IDEA.
 * User: Sai
 * Date: 08/10/11
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JdbcDaoSupport.class)
public class CustomerDaoImplTest_PowerMockito {

    @Mock
    private JdbcDaoSupport jdbcDaoSupport;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private CustomerDao customerDao;

    @Before
    public void setUp() {
        customerDao = new CustomerDaoImpl(jdbcDaoSupport);
    }

    @Test
    public void findById_shouldRetrieveCustomer_GivenAnExistentId() {
        // Given
        final long givenCustomerId = 1L;
        final Customer expectedCustomer = new Customer("sai@test.com");
        when(jdbcDaoSupport.getJdbcTemplate()).thenReturn(jdbcTemplate);
        when(jdbcTemplate.query(eq("select * from customer where id=?"), aryEq(new Object[]{givenCustomerId}), any(CustomRowMapper.class))).thenReturn(Arrays.asList(expectedCustomer));

        // When
        final Customer customer = customerDao.findById(givenCustomerId);

        // Then
        assertThat(customer, is(equalTo(expectedCustomer)));
    }
}
