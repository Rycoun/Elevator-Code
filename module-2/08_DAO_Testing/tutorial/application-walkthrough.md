# Tutorial application walkthrough

The tutorial includes a small console application. The application allows you to see the DAO in action, not only at run time, but you can also examine the code in `TutorialController.java` to see how they're called and the results handled.

In addition, the application displays information that's particularly useful for writing tests. For instance, there is a set of breadcrumbs displayed throughout the run of the application which provide values for the currently selected customer and sale:

```
[ customerId | FullName ] >> [ saleId | total | delivery ]
```

So, if you're interested in testing `getSalesByCustomerId()` for a particular customer, you merely need to select the customer in the application to find their ID in the breadcrumbs. There's no need to root around in the database or set breakpoints and examine values.

Start the tutorial by running `Tutorial.main()` and following along with the walkthrough.

> Note: the application refreshes the `PizzaShopLite` database on startup so you might notice a few seconds delay before the `Main Menu` displays:

```
Welcome to the Pizza Shop.

------------------------------
Main Menu
Selected: --- >> ---
------------------------------
1: Customer - select
2: Sale - create, read, update, delete
3: Exit the program
Please select:
```

Although the Pizza Shop database supports customer-less sales, the application requires that every sale has a customer. With that in mind, select `1: Customer - select` from the `Main Menu`. This displays all the customers in the `PizzaShopLite` database:

```
Customer List
-----------------------------
1: Robin Besnardeau
2: Dud Dobbins
3: Madge Lampaert
4: Deanne Mallon
5: Elenore Mamwell
6: Row Woofenden
Please select:
```

Select `3: Madge Lampaert` from the `Customer List`.

Madge becomes your *selected* customer, and the application returns to the `Main Menu`:

```
------------------------------------------
Main Menu
Selected: [ 3 | Madge Lampaert ] >> ---
------------------------------------------
1: Customer - select
2: Sale - create, read, update, delete
3: Exit the program
Please select:
```

> Note: Madge's ID and full name now appears in the `Selected:` breadcrumbs under the `Main Menu`.

Next, select `2: Sale - create, read, update, delete` from the `Main Menu` to go to the `Sale Menu` and work with Madge's sales:

```
------------------------------------------
Sale Menu
Selected: [ 3 | Madge Lampaert ] >> ---
------------------------------------------
1: Select sale
2: Create new sale
3: Update selected sale
4: Delete selected sale
5: Return to Main Menu
Please select:
```

From the `Sale Menu` select `1: Select sale` to display Madge's existing sales:

```
Sale List
-----------------------------
1: Sale {saleId=5, total=23.98, delivery=true, customerId=3}
2: Sale {saleId=6, total=41.25, delivery=false, customerId=3}
Please select:
```

> Note: the list displays the attribute names and values for each of the `Sale` objects. Once again, this information is particularly useful when you write your tests.

For now, skip selecting a specific sale, press `Enter` to return to the `Sale Menu`:

```
------------------------------------------
Sale Menu
Selected: [ 3 | Madge Lampaert ] >> ---
------------------------------------------
1: Select sale
2: Create new sale
3: Update selected sale
4: Delete selected sale
5: Return to Main Menu
Please select:
```

Select `2: Create new sale` to add a new sale for Madge. Enter values at the `Total:` and `Delivery:` prompts and `Y` to create when asked:

```
Enter new sale information
-----------------------------
Total: 34.56
Delivery (Y/N)?: Y
-----------------------------

Are you sure you wish to create the new sale (Y/N)?: Y

Sale {saleId=12, total=34.56, delivery=true, customerId=3} CREATED !!!
```

> Note: the application doesn't perform any validation. *Please be careful when entering values.*

Provided there are no problems, a confirmation message appears, and the application returns to the `Sale Menu`:

```
-------------------------------------------------
Sale Menu
Selected: [ 3 | Madge Lampaert ] >> [ 12 | $34.56 | true ]
-------------------------------------------------
1: Select sale
2: Create new sale
3: Update selected sale
4: Delete selected sale
5: Return to Main Menu
Please select:
```

> Note: the new sale is *auto-magically* your *selected* sale, and appears in the `Selected:` breadcrumbs under the `Sale Menu`.

If you'd like, you can re-select `1: Select sale` from the `Sale Menu` to display the new sale along with the previously existing sales:

```
Sale List
-----------------------------
1: Sale {saleId=5, total=23.98, delivery=true, customerId=3}
2: Sale {saleId=6, total=41.25, delivery=false, customerId=3}
3: Sale {saleId=12, total=34.56, delivery=true, customerId=3}
Please select:
```

Please make sure to re-select `3: Sale {saleId=12, total=34.36, delivery=true, customerId=3}` to keep the new sale as the *selected* sale and return back to the `Sale Menu`:

```
-------------------------------------------------
Sale Menu
Selected: [ 3 | Madge Lampaert ] >> [ 12 | $34.56 | true ]
-------------------------------------------------
1: Select sale
2: Create new sale
3: Update selected sale
4: Delete selected sale
5: Return to Main Menu
Please select:
```

Select `3: Update selected sale` to make corrections to the new sale. The application displays the existing values and prompts you for the values you can change. Enter only the values you want to change. Leave the prompt blank you if want to accept the existing value:

```
Current sale information
-----------------------------
Sale Id: 12
Total: 34.56
Delivery: true
Customer: 3
-----------------------------

Update sale information
-----------------------------
Total: 65.43
Delivery (Y/N)?: N
-----------------------------
Proposed update to sale
-----------------------------
Sale Id: 12
Total: 65.43
Delivery: false
Customer: 3
-----------------------------

Are you sure you wish to update the sale (Y/N)?: Y

Sale {saleId=12, total=65.43, delivery=false, customerId=3} UPDATED !!!
```

Enter `Y` to update when asked. The application displays a confirmation message when the update is successful and returns you to the `Sale Menu`:

```
--------------------------------------------------
Sale Menu
Selected: [ 3 | Madge Lampaert ] >> [ 12 | $65.43 | false ]
--------------------------------------------------
1: Select sale
2: Create new sale
3: Update selected sale
4: Delete selected sale
5: Return to Main Menu
Please select:
```

The updated sale remains the *selected* sale. Select `4: Delete selected sale` to remove it. 

The application displays *selected* sale information and asks you to confirm the deletion:

```
Current sale information
-----------------------------
Sale Id: 12
Total: 65.43
Delivery: false
Customer: 3
-----------------------------

Are you sure you wish to delete the sale (Y/N)?: Y

Sale {saleId=12, total=65.43, delivery=false, customerId=3} DELETED !!!
```

Now that you have seen all the CRUD methods in action, enter `5: Return to Main Menu` on the `Sale Menu` to return to `Main Menu` where you can select another customer and experiment on your own.

> Reminder, you can see the actual handling of the DAO methods in the application code by examining `TutorialController.java`. Search for `// DAO in action !!!` to find the calls to and handling of the various `JdbcSaleDao` methods.
