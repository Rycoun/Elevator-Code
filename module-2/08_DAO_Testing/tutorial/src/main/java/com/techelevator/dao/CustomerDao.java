package com.techelevator.dao;

import com.techelevator.model.Customer;

import java.util.List;

public interface CustomerDao {

    /**
     * Get a customer from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param customerId the id of the customer to retrieve
     * @return a complete Customer object
     */
    Customer getCustomerById(int customerId);

    /**
     * Get all customers from the datastore
     *
     * @return a List of Customer objects
     */
    List<Customer> getCustomers();
}
