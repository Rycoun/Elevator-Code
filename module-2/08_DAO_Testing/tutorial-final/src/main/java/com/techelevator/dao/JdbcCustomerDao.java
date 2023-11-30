package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        String sql = "SELECT customer_id, first_name, last_name, street_address, city, phone_number, " +
                     "email_address, email_offers " +
                     "FROM customer " +
                     "WHERE customer_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
            if (results.next()) {
                customer = mapRowToCustomer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, street_address, city, phone_number, " +
                     "email_address, email_offers " +
                     "FROM customer " +
                     "ORDER BY last_name, first_name;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Customer customer = mapRowToCustomer(results);
                customers.add(customer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    private Customer mapRowToCustomer(SqlRowSet rowSet) {
        Customer customer = new Customer();
        customer.setCustomerId(rowSet.getInt("customer_id"));
        customer.setFirstName(rowSet.getString("first_name"));
        customer.setLastName(rowSet.getString("last_name"));
        customer.setStreetAddress(rowSet.getString("street_address"));
        customer.setCity(rowSet.getString("city"));
        customer.setPhoneNumber(rowSet.getString("phone_number"));
        customer.setEmailAddress(rowSet.getString("email_address"));
        customer.setEmailOffers(rowSet.getBoolean("email_offers"));
        return customer;
    }
}
