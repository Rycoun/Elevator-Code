package com.techelevator;

import com.techelevator.util.SystemInOutConsole;

public class Tutorial {

    public static void main(String[] args) {
        try {
            SystemInOutConsole systemInOutConsole = new SystemInOutConsole();
            TutorialController controller = new TutorialController(systemInOutConsole);
            controller.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
