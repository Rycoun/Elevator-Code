package com.techelevator;

public class FruitTree {
    private String typeOfFruit;
    private int piecesOfFruitLeft;

    public FruitTree(String typeOfFruit, int startingFruit) {
        this.typeOfFruit = typeOfFruit;
        this.piecesOfFruitLeft = startingFruit;
    }

    public String getTypeOfFruit() {
        return typeOfFruit;
    }

    public int getPiecesOfFruitLeft() {
        return piecesOfFruitLeft;
    }
    public boolean pickFruit(int numToRemove) {
        if (piecesOfFruitLeft >= numToRemove) {
            piecesOfFruitLeft -= numToRemove;
            return true;
        } else {
            return false;
        }
    }


}
