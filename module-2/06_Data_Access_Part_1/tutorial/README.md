# Data access part 1 tutorial

In this tutorial, you'll practice connecting to a database and retrieving data from it in Java. By the end of this tutorial, you'll have written code that:

- Connects to a PostgreSQL database
- Sends SQL queries to the database
- Creates Java objects that correspond to data returned by queries
- Encapsulates the database interactions using the DAO pattern

## Getting started

To get started, follow these steps:

1. Open the tutorial project in IntelliJ.
2. Once the project is open, navigate to the project folder `src/main/java/com/techelevator`.
3. Open the `Tutorial.java` file by double-clicking on the filename.

You'll see the starter code for this project. This project uses the `PizzaShop` database.

> Note: If you don't have the `PizzaShop` database, you can find the `PizzaShop.sql` file in your `resources/postgresql` folder at the top of your repository or get it from your instructor.
> Once you have the `PizzaShop.sql` file, follow the instructions in the `Database setup` lesson in the `PostgreSQL` unit of `Intro to Tools`.

## Step One: Configure the database connection

Before the program can connect to the database, you'll need to provide several pieces of information about the connection.

Find the first comment in `Tutorial.java`. You'll add your code after this line:

```java
// Step One: Configure the database connection
```

Add three lines to set the URL, username, and password for the connection:

```java
dataSource.setUrl("jdbc:postgresql://localhost:5432/PizzaShop");
dataSource.setUsername("postgres");
dataSource.setPassword("postgres1");
```

**Warning:** Including credentials like a username and password in your source code is convenient while learning, but it's a major security risk. In the future, you'll learn how to avoid doing this by using environment variables or configuration files.

After adding those lines, run the `main()` method in the `Tutorial` class. You'll see one line of output:

```
Total Sales: $0
```

## Step Two: Add SQL for retrieving total sales

The `sale` table of the database contains data about the pizza shop's sales. In the DAO pattern, there are three Java files associated with a database table. For the `sale` table, those three files are:

- `Sale.java`: a class used for objects that each correspond to a row of the `sale` table
- `SaleDao.java`: an interface that specifies the available methods for getting information from the `sale` table
- `JdbcSaleDao.java`: a class that implements the methods specified by the `SaleDao` interface

The `JdbcSaleDao` class encapsulates the logic and SQL used to retrieve data from the `sale` table. Open that class and look at the `getTotalSales()` method:

```java
public BigDecimal getTotalSales() {
    BigDecimal total = null;

    // Step Two: Add SQL for retrieving total sales
    String sql = "SELECT 0 AS total;";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

    if (results.next()) {
        total = results.getBigDecimal("total");
    }

    return total;
}
```

This method follows the typical pattern for retrieving data from a SQL database in Java. The code declares a string for the SQL query and passes it to the `queryForRowSet()` method of an instance of Spring Framework's `JdbcTemplate` class. That method returns a `SqlRowSet` object, which it begins to read with `results.next()`. The query returns a single value, so it uses `getBigDecimal()` and the column name to access the value.

Currently, this code isn't querying anything in the database because the query is `SELECT 0 AS total;` which means the total returned is always zero.

Replace that query with `SELECT SUM(total) AS total FROM sale;`.

Run the `Tutorial` class again, and you'll see that the output has changed to this:

```
Total Sales: $1248.80
```

## Step Three: Copy returned values into an object

The `getTotalSales()` method retrieved a single value from the database, but the query sent to the database in the `getSaleById()` method returns an entire row of data. The `mapRowToSale()` method handles creating a `Sale` object from that row of data. Currently, however, it's returning an empty object.

To set the properties of the object with the values from the row of data, add this code following the "Step Three" comment in `JdbcSaleDao.java`:

```java
sale.setSaleId(rowSet.getInt("sale_id"));
sale.setTotal(rowSet.getBigDecimal("total"));
sale.setDelivery(rowSet.getBoolean("is_delivery"));
sale.setCustomerId(rowSet.getInt("customer_id"));
if (rowSet.wasNull()) {
    sale.setCustomerId(null);
}
```

Each line calls a setter and passes it a value retrieved from the `SqlRowSet`. Notice that there are different methods to use for each data type: `getInt()` for `int` values, `getBoolean()` for `boolean` values, and so on.

Also notice that the handling of the `customerId` property is a little different. You must also check if the last value retrieved from the database was null. That extra check is necessary because the column can have null values, but the `getInt()` method can't return null.

After adding those lines, return to the `Tutorial` class. Following the "Step Three" comment, add this code to the `run()` method to get a `Sale` object representing the sale with an id of 50, and print it out:

```java
Sale sale50 = saleDao.getSaleById(50);
System.out.println(sale50);
```

Run the `Tutorial` class again, and you'll see that the output has changed to this:

```
Total Sales: $1248.80
Sale 50: $12.99 (carryout)
```

## Step Four: Add a new DAO method

In addition to the DAO for the `sale` table, this project also contains a DAO for the `customer` table. It consists of three files: `Customer.java`, `CustomerDao.java`, and `JdbcCustomerDao.java`. You were able to retrieve a `Sale` object based on its id in the `sale` table using the `getSaleById()` method of the `SaleDao` interface, but there's currently no way to retrieve a `Customer` object based on its id in the `customer` table.

To add that capability, begin by updating the `CustomerDao` interface with a new method declaration following the "Step Four" comment:

```java
Customer getCustomerById(int customerId);
```

Remember that interfaces don't normally contain implementations of methods. Interfaces are like contracts, specifying what methods the implementing class—`JdbcCustomerDao` in this case—must have. The interface is an important part of the DAO pattern because it helps make code loosely coupled, which facilitates testing and reuse.

Now that you've added the declaration of `getCustomerById()` to the `CustomerDao` interface, implement the method in the `JdbcCustomerDao` class following the "Step Four" comment:

```java
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
```

Notice that the SQL query used in this method contains a `?`. That `?` is a placeholder for a **parameter**, or a value that's specified when you send the query to the database. You can see that on the next line where `queryForRowSet()` gets passed both the string containing the SQL and the value to use for that parameter, which is `customerId` in this case.

Now that the `CustomerDao` interface offers a `getCustomerById()` method, return to the `Tutorial` class and add these lines to the `run()` method to display information about the customer associated with the sale you retrieved data about earlier:

```java
Customer customerForSale50 = customerDao.getCustomerById(sale50.getCustomerId());
System.out.println("Customer for that sale was " + customerForSale50);
```

Run the `Tutorial` class again, and you'll see that the output looks like this:

```
Total Sales: $1248.80
Sale 50: $12.99 (carryout)
Customer for that sale was Elenore Mamwell
```

## Step Five: Call a DAO method that returns a `List`

In addition to returning values or single objects, DAO methods frequently return a `List` of objects. You can find an example of this if you look at the `getCustomersByName()` declaration in the `CustomerDao` interface. As the comments document, this method returns a `List` of `Customer` objects for customers whose first name or last name contains the specified search string.

Switch to the `JdbcCustomerDao` and look at the implementation of the method. It's similar to the method you added in the previous step, with a few important differences to notice:

- The SQL query has two parameters, so there are two `?` symbols. The `queryForRowSet()` method sets the values for these parameters. In this case, both parameters use the same value, so `search` appears twice.
- The SQL query uses a `LIKE` for searching. If `useWildCard` is true, the parameter value has `%` symbols added to it rather than included in the SQL.
- Since it's expected that the query returns multiple rows, a `while` loop iterates through those rows and create a `Customer` object for each.

After reviewing that method, return to the `Tutorial` class and add these lines to the `run()` method to search for all customers with "Ma" in their first or last name:

```java
List<Customer> matchingCustomers = customerDao.getCustomersByName("Ma", true);
System.out.println("All customers with \"Ma\" in their first or last name:");
for (Customer customer : matchingCustomers) {
    System.out.println(customer);
}
```

Run the `Tutorial` class again, and you'll see that the output is now this:

```
Total Sales: $1248.80
Sale 50: $12.99 (carryout)
Customer for that sale was Elenore Mamwell
All customers with "Ma" in their first or last name:
Deanne Mallon
Madge Lampaert
Elenore Mamwell
Margaret Peepall
Raquel Marcome
```

## Next steps

Now that you've seen the DAO pattern and how it's used to retrieve information from the database, there are many possibilities for further experimentation.

For example, you could add additional methods to the `SaleDao` or `CustomerDao` interfaces, like a method for retrieving all the sales associated with a particular customer or a method for retrieving all the customers who want to receive email offers.

Another possibility would be to add a new DAO for one of the other tables in the `PizzaShop` database. If you chose the `pizza` table, you'd create a `Pizza` class, a `PizzaDao` interface, and a `JdbcPizzaDao` class.
