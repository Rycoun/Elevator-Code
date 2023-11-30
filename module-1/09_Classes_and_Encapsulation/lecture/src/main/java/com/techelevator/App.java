package com.techelevator;

public class App {
    public static void main(String[] args) {
        UserProfile walsProfile = new UserProfile("Walt", "imp", "Walt@tech");

        UserProfile tomProfile = new UserProfile("Tom", "Imp", "tom@inf");

        String waltFirstName = walsProfile.getFirstName();
        System.out.println(waltFirstName);
        walsProfile.setFirstName("Walter");
        waltFirstName = walsProfile.getFirstName();
        System.out.println(waltFirstName);

    }


}
