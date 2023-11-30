package com.techelevator.dao;

import com.techelevator.model.Sale;

import java.util.List;

public interface SaleDao {

    /**
     * Get the sale from the datastore with the given id.
     *
     * @param saleId the id of the sale to retrieve
     * @return a complete Sale object
     */
    Sale getSaleById(int saleId);

    /**
     * Get all sales from the datastore with the given customer id.
     *
     * @param customerId the id of the customer whose sales to retrieve
     * @return a List of Sale objects
     */
    List<Sale> getSalesByCustomerId(int customerId);

    /**
     * Create a new sale in the datastore with the given information.
     *
     * @param sale the sale information to add
     * @return Sale object with the id populated
     */
    Sale createSale(Sale sale);

    /**
     * Update an existing sale in the datastore with the given information.
     *
     * @param sale the sale information to update
     * @return Updated Sale object
     */
    Sale updateSale(Sale sale);

    /**
     * Delete the sale with the given id.
     *
     * @param saleId the id of the sale to delete
     * @return Number of sales deleted
     */
    int deleteSaleById(int saleId);
}
