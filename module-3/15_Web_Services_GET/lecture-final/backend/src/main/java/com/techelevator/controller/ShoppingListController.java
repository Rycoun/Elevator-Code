package com.techelevator.controller;

import com.techelevator.model.shoppinglist.ShoppingList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:8080"})
@RestController
public class ShoppingListController {

    @RequestMapping(path = "/shopping-list", method = RequestMethod.GET)
    public ShoppingList getList() {

        ShoppingList shoppingList = new ShoppingList();

        shoppingList.addItem("Bread");
        shoppingList.addItem("Milk");
        shoppingList.addItem("Eggs");

        return shoppingList;
    }
}
