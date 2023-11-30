package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import org.springframework.dao.DataIntegrityViolationException;
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
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        if (results.next()) {
            customer = mapRowToCustomer(results);
        }
        return customer;
    }

    @Override
    public List<Customer> getCustomersByName(String search, boolean useWildCard) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, street_address, city, phone_number, " +
                "email_address, email_offers " +
                "FROM customer " +
                "WHERE first_name LIKE ? OR last_name LIKE ?;";

        if (useWildCard) {
            search = "%" + search + "%";
        }

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, search, search);
        while (results.next()) {
            customers.add(mapRowToCustomer(results));
        }
        return customers;
    }

    // Step One: Create a new customer
    @Override
    public Customer createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, street_address, city, phone_number, " +
                "email_address, email_offers) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING customer_id;";

        int customerId = jdbcTemplate.queryForObject(sql, int.class, customer.getFirstName(), customer.getLastName(),
                customer.getStreetAddress(), customer.getCity(), customer.getPhoneNumber(),
                customer.getEmailAddress(), customer.getEmailOffers());

        return getCustomerById(customerId);
    }

    // Step Two: Update an existing customer
    @Override
    public Customer updateCustomer(Customer customer) {
        String sql = "UPDATE customer " +
                "SET first_name=?, last_name=?, street_address=?, city=?, phone_number=?, email_address=?, email_offers=? " +
                "WHERE customer_id=?;";

        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getStreetAddress(), customer.getCity(),
                customer.getPhoneNumber(), customer.getEmailAddress(), customer.getEmailOffers(), customer.getCustomerId());

        return getCustomerById(customer.getCustomerId());
    }

    // Step Three: Delete a customer
    @Override
    public int deleteCustomerById(int customerId) {
        try {
            String sql = "DELETE FROM customer WHERE customer_id = ?;";

            return jdbcTemplate.update(sql, customerId);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Error deleting customer " + customerId, e);
        }
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
