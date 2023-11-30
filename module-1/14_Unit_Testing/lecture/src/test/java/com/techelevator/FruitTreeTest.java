package com.techelevator;

import com.techelevator.fruittree.FruitTree;
import org.junit.Assert;
import org.junit.Test;

public class FruitTreeTest {

    @Test
    public void constructor_correctly() {
        int startPieces = 10;
        String fruitType = "Apple";

        FruitTree fruitTree = new FruitTree(fruitType, startPieces);

        Assert.assertEquals(startPieces, fruitTree.getPiecesOfFruitLeft());
        Assert.assertEquals(fruitType, fruitTree.getTypeOfFruit());
    }

    @Test
    public void pickFruit_does_not_pick_given_negative_Value() {
        FruitTree fruitTree = new FruitTree("Apple", 10);

        boolean didPick = fruitTree.pickFruit(-4);

        Assert.assertFalse(didPick);
        Assert.assertEquals(10, fruitTree.getPiecesOfFruitLeft());
    }

    @Test
    public void pickFruit_does_not_pick_given_too_many() {
        FruitTree fruitTree = new FruitTree("Apple", 10);

        boolean didPick = fruitTree.pickFruit(11);

        Assert.assertFalse(didPick);
        Assert.assertEquals(10, fruitTree.getPiecesOfFruitLeft());
    }
    @Test
    public void pickFruit_does_not_pick_given_just_enough() {
        FruitTree fruitTree = new FruitTree("Apple", 10);

        boolean didPick = fruitTree.pickFruit(10);

        Assert.assertTrue(didPick);
        Assert.assertEquals(0, fruitTree.getPiecesOfFruitLeft());
    }



}
