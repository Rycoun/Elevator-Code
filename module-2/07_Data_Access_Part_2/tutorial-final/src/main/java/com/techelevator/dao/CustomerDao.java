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
     * Get customers whose first or last names include the given search string.
     *
     * @param search the string to search for in customer names
     * @param useWildCard the boolean to control whether to wrap the search term with wild-card characters
     * @return a List of Customer objects
     */
    List<Customer> getCustomersByName(String search, boolean useWildCard);

    // Step One: Create a new customer
    /**
     * Create a new customer in the datastore with the given information.
     *
     * @param customer the customer information to add
     * @return Customer object with the id populated
     */
    Customer createCustomer(Customer customer);

    // Step Two: Update an existing customer
    /**
     * Update an existing customer in the datastore with the given information.
     *
     * @param customer the customer information to update
     * @return Updated Customer object
     */
    Customer updateCustomer(Customer customer);

    // Step Three: Delete a customer
    /**
     * Delete the customer with the given id.
     *
     * @param customerId the id of the customer to delete
     * @return Number of customers deleted
     */
    int deleteCustomerById(int customerId);
}
