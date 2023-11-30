package com.techelevator;

import com.techelevator.fruittree.FruitTree;
import org.junit.Assert;
import org.junit.Test;

public class FruitTreeTest {

    @Test
    public void constructor_initializes_correctly() {
        // arrange
        int startingPieces = 10;
        String fruitType = "Apple";

        // act
        FruitTree fruitTree = new FruitTree(fruitType, startingPieces);

        // assert
        Assert.assertEquals(startingPieces, fruitTree.getPiecesOfFruitLeft());
        Assert.assertEquals(fruitType, fruitTree.getTypeOfFruit());
    }

    @Test
    public void pickFruit_does_not_pick_given_negative_value() {
        // arrange
        int startingPieces = 10;
        FruitTree fruitTree = new FruitTree("Apple", startingPieces);

        // act
        boolean didPick = fruitTree.pickFruit(-4);

        // assert
        Assert.assertFalse(didPick);
        Assert.assertEquals(startingPieces, fruitTree.getPiecesOfFruitLeft());
    }

    @Test
    public void pickFruit_does_not_pick_given_too_many() {
        // arrange
        int startingPieces = 10;
        FruitTree fruitTree = new FruitTree("Apple", startingPieces);

        // act
        boolean didPick = fruitTree.pickFruit(11);

        // assert
        Assert.assertFalse(didPick);
        Assert.assertEquals(startingPieces, fruitTree.getPiecesOfFruitLeft());
    }

    @Test
    public void pickFruit_does_pick_given_just_enough() {
        // arrange
        int startingPieces = 10;
        FruitTree fruitTree = new FruitTree("Apple", startingPieces);

        // act
        boolean didPick = fruitTree.pickFruit(10);

        // assert
        Assert.assertTrue(didPick);
        Assert.assertEquals(0, fruitTree.getPiecesOfFruitLeft());
    }

}
