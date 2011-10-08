package org.sai.play.dao;

import org.sai.play.model.Customer;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sai
 * Date: 08/10/11
 * Time: 09:35
 * To change this template use File | Settings | File Templates.
 */
public class CustomerDaoImpl implements CustomerDao {

    private static final String FIND_BY_ID = "select * from customer where id=?";

    private final JdbcDaoSupport jdbcDaoSupport;

    public CustomerDaoImpl(final JdbcDaoSupport jdbcDaoSupport) {
        this.jdbcDaoSupport = jdbcDaoSupport;
    }

    public Customer findById(final Long id) {
        List<Customer> customers = jdbcDaoSupport.getJdbcTemplate().query(FIND_BY_ID, new Object[]{id}, new CustomRowMapper<Customer>());
        if (customers.isEmpty()) {
            throw new RuntimeException("No customer found for id " + id);
        }
        return customers.get(0);
    }


}
