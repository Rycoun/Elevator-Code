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
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Lou");
        newCustomer.setLastName("Malnati");
        newCustomer.setStreetAddress("6649 North Lincoln Avenue");
        newCustomer.setCity("Lincolnwood");
        newCustomer.setPhoneNumber("8476730800");
        newCustomer.setEmailAddress("lou@loutmalnatis.com");
        newCustomer.setEmailOffers(true);

        newCustomer = customerDao.createCustomer(newCustomer);
        System.out.println("New customer created with ID " + newCustomer.getCustomerId());

        // Step Two: Update an existing customer
        newCustomer.setFirstName("Louis");
        customerDao.updateCustomer(newCustomer);

        Customer updatedCustomer = customerDao.getCustomerById(newCustomer.getCustomerId());
        System.out.println("In the datastore, updated customer's first name is now " + updatedCustomer.getFirstName());

        // Step Three: Delete a customer
        int numDeleted = customerDao.deleteCustomerById(updatedCustomer.getCustomerId());
        if (numDeleted == 1) {
            System.out.println("Successfully deleted customer");
        } else {
            System.out.println("Customer NOT deleted");
        }

        // Step Four: Delete a customer with sales
        List<Customer> customer = customerDao.getCustomersByName("Marcome", false);
        try {
            customerDao.deleteCustomerById(customer.get(0).getCustomerId());
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }
}
