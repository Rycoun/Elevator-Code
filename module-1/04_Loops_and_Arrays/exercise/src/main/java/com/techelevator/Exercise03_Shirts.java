package com.techelevator;

public class Exercise03_Shirts {

    private final static char SMALL_TSHIRT = 'S';
    private final static char MEDIUM_TSHIRT = 'M';
    private final static char LARGE_TSHIRT = 'L';

    /*
    A t-shirt company rep from Top Threads just finished taking an order
    from a client and needs to key it into the system. The client ordered,
    3 Small shirts ('S'), 2 Medium shirts ('M'), and 1 Large shirt ('L').

    Implement the logic to reflect an order of 6 t-shirts.

    Examples:
    buildOrder() → ['S', 'S', 'S', 'M', 'M', 'L']
     */
    public char[] buildOrder() {
        int shirts = 6;
        char[] order = new char[shirts];

        int smallShirt = 3;
        for (int i = 0; i < smallShirt; i++) {
            order[i] = SMALL_TSHIRT;
        }
        int mediumShirt = 2;
        for (int i = smallShirt; i < smallShirt + mediumShirt; i++) {
            order[i] = MEDIUM_TSHIRT;
        }
        int largeShirt = 3;
        for (int i = smallShirt + mediumShirt; i < shirts; i++) {
            order[i] = LARGE_TSHIRT;
        }
        return order;
    }


    /*
    Another customer called in and is hosting a large networking event and
    needs a bulk order. Rather than indicate how many of each shirt they
    wanted, they've asked for as even distribution as possible.

    Implement the logic to generate an order representing as even a distribution
    as possible, for example: ('S', 'M', 'L', 'S', 'M', 'L', 'S', ...)

    Note: The number of shirts ordered is guaranteed to be non-negative.

    Examples:
    buildBulkOrder(6) → ['S', 'M', 'L', 'S', 'M', 'L']    
    buildBulkOrder(3) → ['S', 'M', 'L']
    buildBulkOrder(4) → ['S', 'M', 'L', 'S']
    buildBulkOrder(0) → []
     */
    public char[] buildBulkOrder(int numberOfShirts) {
        char[] order = new char[numberOfShirts];
        if (numberOfShirts > 0) {
            char[] size = {SMALL_TSHIRT, MEDIUM_TSHIRT, LARGE_TSHIRT};
            int newSize = 0;
            for (int i = 0; i < numberOfShirts; i++) {
                order[i] = size[newSize];
                newSize = (newSize + 1) % size.length;
            }
        }
        return order;
    }

    /*
    The warehouse is out of small shirts and will only request more when the
    next order comes in that includes an 'S' shirt.

    Implement the logic to look through the next incoming order `char[] order`
    and return true if we need to place an order for Small shirts.

    Examples:
    placeRequest(['M', 'L', 'S']) → true
    placeRequest(['M', 'S', 'L']) → true
    placeRequest(['M', 'M', 'L']) → false
    placeRequest([]) → false
     */
    public boolean placeRequest(char[] order) {
        for (char size : order) {
            if (size == SMALL_TSHIRT) {
                return true;
            }
        }
        return false;
    }
}

