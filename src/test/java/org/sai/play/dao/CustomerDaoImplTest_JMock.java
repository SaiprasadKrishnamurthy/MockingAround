package org.sai.play.dao;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.sai.play.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * <p>JMock version</p>
 *
 * @author Sai
 */
public class CustomerDaoImplTest_JMock {

    private Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };
    private CustomerDao customerDao;
    private JdbcDaoSupport jdbcDaoSupport;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = context.mock(JdbcTemplate.class);
        jdbcDaoSupport = new MyTestJdbcDaoSupport(jdbcTemplate);
        customerDao = new CustomerDaoImpl(jdbcDaoSupport);
    }

    @Test
    public void findById_shouldRetrieveCustomer_GivenAnExistentId() throws Exception {
        // Given
        final long givenCustomerId = 1L;
        final Customer expectedCustomer = new Customer("sai@test.com");
        context.checking(new Expectations() {{
            oneOf(jdbcTemplate).query(with("select * from customer where id=?"), with(new Object[]{givenCustomerId}), with(any(CustomRowMapper.class)));
            will(returnValue(Arrays.asList(expectedCustomer)));
        }});

        // When
        Customer actualCustomer = customerDao.findById(givenCustomerId);

        // Then
        assertThat(actualCustomer, is(equalTo(expectedCustomer)));
    }

    // Created because JdbcDaoSupport cannot be mocked because it's setJdbcTemplate is a final method.
    private class MyTestJdbcDaoSupport extends JdbcDaoSupport {
        private final JdbcTemplate testJdbcTemplate;


        public MyTestJdbcDaoSupport(final JdbcTemplate testJdbcTemplate) {
            this.testJdbcTemplate = testJdbcTemplate;
            super.setJdbcTemplate(this.testJdbcTemplate);
        }

        public JdbcTemplate getTestJdbcTemplate() {
            return this.testJdbcTemplate;
        }
    }
}
