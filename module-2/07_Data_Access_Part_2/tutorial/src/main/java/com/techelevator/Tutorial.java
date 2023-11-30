package com.techelevator;

import com.techelevator.dao.CustomerDao;
import com.techelevator.dao.JdbcCustomerDao;
import com.techelevator.dao.JdbcSaleDao;
import com.techelevator.dao.SaleDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import com.techelevator.model.Sale;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class Tutorial {

    private SaleDao saleDao;
    private CustomerDao customerDao;

    public Tutorial(DataSource dataSource) {
        saleDao = new JdbcSaleDao(dataSource);
        customerDao = new JdbcCustomerDao(dataSource);
    }

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/PizzaShop");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        Tutorial tutorial = new Tutorial(dataSource);
        tutorial.run();
    }

    private void run() {

        // Step One: Create a new customer

        // Step Two: Update an existing customer

        // Step Three: Delete a customer

        // Step Four: Delete a customer with sales
    }
}
