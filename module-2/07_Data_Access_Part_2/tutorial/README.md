# Data access part 2 tutorial

In this tutorial, you'll practice sending additional types of SQL statements to a database from Java. By the end of this tutorial, you'll have written code that:

- Sends `INSERT`, `UPDATE`, and `DELETE` SQL statements
- Retrieves the IDs of newly inserted rows
- Handles exceptions that can occur when interacting with a database

## Getting started

To get started, follow these steps:

1. Open the tutorial project in IntelliJ.
2. Once the project is open, navigate to the project folder `src/main/java/com/techelevator`.
3. Open the `Tutorial.java` file by double-clicking on the filename.

You'll see the starter code for this project. This project uses the `PizzaShop` database.

> Note: If you don't have the `PizzaShop` database, you can find the `PizzaShop.sql` file in your `resources/postgresql` folder at the top of your repository or get it from your instructor.
> Once you have the `PizzaShop.sql` file, follow the instructions in the `Database setup` lesson in the `PostgreSQL` unit of `Intro to Tools`.

## Before you begin

Take a moment to look through the starter code. This project starts where the previous tutorial ended. This project uses all of the code and concepts from that tutorial: the DAO pattern, loose coupling, `JdbcTemplate`, etc.

## Step One: Create a new customer

To complete this step, you'll add code to three Java files: `CustomerDao`, `JdbcCustomerDao`, and `Tutorial`. 

Look for the following comment in each of the files
```java
// Step One: Create a new customer
```
and add the code below.

Currently, `CustomerDao` can only retrieve Customer objects from the datastore. You can add additional methods to provide more features. 

Add this method to the `CustomerDao` interface:

```java
/**
 * Create a new customer in the datastore with the given information.
 *
 * @param customer the customer information to add
 * @return Customer object with the id populated
 */
Customer createCustomer(Customer customer);
```

Adding this method to the interface requires `JdbcCustomerDao` which implements the interface to provide an implementation of the method.

Add this method to `JdbcCustomerDao`:

```java
@Override
public Customer createCustomer(Customer customer) {
    String sql = "INSERT INTO customer (first_name, last_name, street_address, city, phone_number, " +
            "email_address, email_offers) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING customer_id;";

    int customerId = jdbcTemplate.queryForObject(sql, int.class, customer.getFirstName(), customer.getLastName(),
            customer.getStreetAddress(), customer.getCity(), customer.getPhoneNumber(),
            customer.getEmailAddress(), customer.getEmailOffers());

    return getCustomerById(customerId);
}
```

Review the code of `createCustomer`. It contains a SQL `INSERT` statement with bind parameters (`?`'s) for all of the values. Even though the SQL inserts data, and you might expect the code to use the `JdbcTemplate.update` method, it uses the `queryForObject` method instead. This is due to the `RETURNING customer_id` clause in the SQL. It causes Postgres to return the `customer_id` of the new row as a result set just like a `SELECT` statement. The `int.class` parameter tells `queryForObject` to retrieve the value from the result set as an integer. 

Add this code to `Tutorial`. When executed it adds Lou Malnati to the datastore:

```java
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
```

## Step Two: Update an existing customer

In Step Two, you will look for the following comment in each of the three files.
```java
// Step Two: Update an existing customer
```

To update a customer you must again add a method declaration to the interface `CustomerDao` and an implementation in `JdbcCustomerDao`. 

Add this code to `CustomerDao`:

```java
/**
 * Update an existing customer in the datastore with the given information.
 *
 * @param customer the customer information to update
 * @return Updated Customer object
 */
Customer updateCustomer(Customer customer);
```

Add this code to `JdbcCustomerDao`:

```java
@Override
public Customer updateCustomer(Customer customer) {
        String sql = "UPDATE customer " +
                "SET first_name=?, last_name=?, street_address=?, city=?, phone_number=?, email_address=?, email_offers=? " +
                "WHERE customer_id=?;";

        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getStreetAddress(), customer.getCity(),
                customer.getPhoneNumber(), customer.getEmailAddress(), customer.getEmailOffers(), customer.getCustomerId());

        return getCustomerById(customer.getCustomerId());
}
```
This implementation uses the `JdbcTemplate.update` method since the `UPDATE` statement doesn't return a result set.

Add this code to `Tutorial` to call the new method and ensure it works properly.

```java
newCustomer.setFirstName("Louis");
customerDao.updateCustomer(newCustomer);

Customer updatedCustomer = customerDao.getCustomerById(newCustomer.getCustomerId());
System.out.println("In the datastore, updated customer's first name is now " + updatedCustomer.getFirstName());
```

## Step Three: Delete a customer

As before add code to both `CustomerDao` and `JdbcCustomerDao` under the following comment.
```java
// Step Three: Delete a customer
```
Add this to `CustomerDao`:

```java
/**
 * Delete the customer with the given id.
 *
 * @param customerId the id of the customer to delete
 * @return Number of customers deleted
 */
int deleteCustomerById(int customerId);
```

Add this code to `JdbcCustomerDao`:

```java
@Override
public int deleteCustomerById(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?;";

        return jdbcTemplate.update(sql, customerId);
}
```

The implementation still uses the `JdbcTemplate.update` method even though it's executing a `DELETE` statement.

In `Tutorial` add this code to remove Louis Malnati from the datastore.

```java
int numDeleted = customerDao.deleteCustomerById(updatedCustomer.getCustomerId());
if (numDeleted == 1) {
        System.out.println("Successfully deleted customer");
} else {
        System.out.println("Customer NOT deleted");
}
```

## Step Four: Delete a customer with sales

The customer Raquel Marcome has two sales in the datastore. Add this code to `Tutorial` to delete Raquel Marcome from the datastore:

```java
List<Customer> customer = customerDao.getCustomersByName("Marcome", false);
customerDao.deleteCustomerById(customer.get(0).getCustomerId());
```

Run `Tutorial`. It throws an exception similar to this:

```java
Exception in thread "main" org.springframework.dao.DataIntegrityViolationException: PreparedStatementCallback; SQL [DELETE FROM customer WHERE customer_id = ?;]; ERROR: update or delete on table "customer" violates foreign key constraint "fk_sale_customer_id" on table "sale"
  Detail: Key (customer_id)=(42) is still referenced from table "sale".; nested exception is org.postgresql.util.PSQLException: ERROR: update or delete on table "customer" violates foreign key constraint "fk_sale_customer_id" on table "sale"
  Detail: Key (customer_id)=(42) is still referenced from table "sale".
	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:247)
	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)
...
```

The `DELETE` fails because there are rows in the `sale` table with foreign keys to Raquel Marcome. Code could be added to `Tutorial` to catch a `DataIntegrityViolationException` but that would tightly couple `Tutorial` with the JDBC implementation of `CustomerDao`. 

Note that the starter code contains the class `com.techelevator.exception.DaoException`. In `JdbcCustomerDao` replace the implementation of `deleteCustomerById` with this version:

```java
@Override
public int deleteCustomerById(int customerId) {
        try {
                String sql = "DELETE FROM customer WHERE customer_id = ?;";

                return jdbcTemplate.update(sql, customerId);
        } catch (DataIntegrityViolationException e) {
                throw new DaoException("Error deleting customer " + customerId, e);
        }
}
```

The code catches a JDBC-specific exception and throws a new exception not tied to a specific implementation.

In `Tutorial` wrap the call to `customerDao.deleteCustomerById` in a `try/catch`:

```java
try {
        customerDao.deleteCustomerById(customer.get(0).getCustomerId());
} catch (DaoException e) {
        System.out.println(e.getMessage());
}
```

Run `Tutorial` and note that it prints the exception's message.

## Next steps

`CustomerDao` correctly handles data integrity violations. But what happens if the application can't connect to the
database or there's a syntax error in the SQL? Change the name of the database in the connection
string to something invalid such as `PizzaShopXYZ`. Does the current `catch` block handle the exception
that's thrown? 

Similarly, change the SQL to something invalid such as `DELET` instead of `DELETE`. What new exception,
if any, is thrown by the code?
