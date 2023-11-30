package com.techelevator;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class USCitiesAndParksCLI {

    private final Scanner userInput = new Scanner(System.in);

    private final StateDao stateDao;
    private final CityDao cityDao;
    private final ParkDao parkDao;

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/UnitedStates");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        USCitiesAndParksCLI application = new USCitiesAndParksCLI(dataSource);
        application.run();
    }

    public USCitiesAndParksCLI(DataSource dataSource) {
        stateDao = new JdbcStateDao(dataSource);
        cityDao = new JdbcCityDao(dataSource);
        // cityDao = new MemoryCityDao();
        parkDao = new JdbcParkDao(dataSource);
    }

    private void run() {
        displayBanner();
        boolean running = true;
        while (running) {
            displayMenu();
            int selection = promptForInt("Please select an option: ");
            if (selection == 1) {
                viewCityInfo();
            } else if (selection == 2) {
                viewParkInfo();
            } else if (selection == 3) {
                running = false;
            } else {
                displayError("Invalid option selected.");
            }
        }
    }

    private void viewCityInfo() {
        int cityCount = getCityCount();
        System.out.println();
        System.out.println("There are " + cityCount + " cities available");
        System.out.println();
        if (cityCount > 0) {
            System.out.format("The largest city has a population of %,d\n", getLargestPopulation());
            System.out.format("The smallest city has a population of %,d\n", getSmallestPopulation());
            System.out.println();
            System.out.format("The average city area is %,.2f sq. km\n", getAverageCityArea());
            System.out.println();
            System.out.println("1. View a specific city");
            System.out.println("2. View a random city");
            System.out.println("3. View a list of city names");
            int selection = promptForInt("Please select an option: ");
            if (selection == 1) {
                City city = promptForCity();
                System.out.println();
                displayCity(city);
            } else if (selection == 2) {
                System.out.println();
                City city = getRandomCity();
                displayCity(city);
            } else if (selection == 3) {
                System.out.println();
                displayCityNames();
            } else {
                displayError("Invalid option selected.");
            }
        }
    }

    private int getCityCount() {
        int cityCount = 0;
        cityCount = cityDao.getCityCount();
        return cityCount;
    }

    private int getLargestPopulation() {
        int population = 0;
        population = cityDao.getMostPopulatedCity();
        return population;
    }

    private int getSmallestPopulation() {
        int population = 0;
        population = cityDao.getLeastPopulatedCity();
        return population;
    }

    private double getAverageCityArea() {
        double area = 0.0;
        area = cityDao.getAverageCityArea();
        return area;
    }

    private City getRandomCity() {
        City city = null;
        city = cityDao.getRandomCity();
        return city;
    }

    private void displayCityNames() {
        System.out.println("The following cities are available: ");
        for (String cityName : cityDao.getCityNames()) {
            System.out.println(cityName);
        }
        System.out.println();
    }

    private void viewParkInfo() {
        int parkCount = getParkCount();
        System.out.println();
        System.out.println("There are " + parkCount + " parks available");
        System.out.println();
        if (parkCount > 0) {
            System.out.println("The oldest park was established in " + getOldestParkDate());
            System.out.println();
            System.out.format("The average park area is %,.2f sq. km\n", getAverageParkArea());
            System.out.println();
            System.out.println("1. Find a park by name");
            System.out.println("2. Find a park by state");
            System.out.println("3. View a random park");
            System.out.println("4. View a list of parks with camping");
            System.out.println("5. View a list of park names");
            int selection = promptForInt("Please select an option: ");
            if (selection == 1) {
                Park park = promptForParkByName();
                System.out.println();
                displayPark(park);
            } else if (selection == 2) {
                Park park = promptForParkByState();
                System.out.println();
                displayPark(park);
            } else if (selection == 3) {
                System.out.println();
                Park park = getRandomPark();
                displayPark(park);
            } else if (selection == 4) {
                System.out.println();
                listParksWithCamping();
            } else if (selection == 5) {
                System.out.println();
                DisplayParkNames();
            } else {
                displayError("Invalid option selected.");
            }
        }
    }

    private int getParkCount() {
        int parkCount = 0;
        parkCount = parkDao.getParkCount();
        return parkCount;
    }

    private LocalDate getOldestParkDate() {
        LocalDate parkDate = null;
        parkDate = parkDao.getOldestParkDate();
        return parkDate;
    }

    private double getAverageParkArea() {
        double area = 0.0;
        area = parkDao.getAverageParkArea();
        return area;
    }

    private Park getRandomPark() {
        Park park = null;
        park = parkDao.getRandomPark();
        return park;
    }

    private void DisplayParkNames() {
        System.out.println("The following parks are available: ");
        for (String parkName : parkDao.getParkNames()) {
            System.out.println(parkName);
        }
        System.out.println();
    }

    private void listParksWithCamping() {
        System.out.println("The following parks offer camping: ");
        for (Park park : parkDao.getParksWithCamping()) {
            displayPark(park);
        }
    }

    private void displayBanner() {
        System.out.println("-----------------------------------------");
        System.out.println("|    US Cities and Parks Information    |");
        System.out.println("-----------------------------------------");
    }

    private void displayMenu() {
        System.out.println("1. View city information");
        System.out.println("2. View park information");
        System.out.println("3. Exit");
    }

    private void displayError(String message) {
        System.out.println();
        System.out.println("***" + message + "***");
        System.out.println();
    }

    private void displayCity(City city) {
        if (city != null) {
            System.out.println(city);
            System.out.format("Population: %,d Area: %,.1f sq. km\n", city.getPopulation(), city.getArea());
        } else {
            System.out.println("City is null");
        }
        System.out.println();
    }

    private void displayPark(Park park) {
        if (park != null) {
            System.out.println(park);
            System.out.format("Established: %s Area: %,.1f sq. km\n", park.getDateEstablished(), park.getArea());
            System.out.format("This park %s camping.\n", park.getHasCamping() ? "offers" : "does not offer");
        } else {
            System.out.println("Park is null");
        }
        System.out.println();
    }

    private int promptForInt(String prompt) {
        return (int) promptForDouble(prompt);
    }

    private double promptForDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = userInput.nextLine();
            try {
                return Double.parseDouble(response);
            } catch (NumberFormatException e) {
                if (response.isBlank()) {
                    return -1; //Assumes negative numbers are never valid entries.
                } else {
                    displayError("Numbers only, please.");
                }
            }
        }
    }

    private State promptForState() {
        while (true) {
            System.out.print("Please enter a state abbreviation (enter ? to list state abbreviations): ");
            String response = userInput.nextLine();
            if (response.equals("?")) {
                for (State state : stateDao.getStates()) {
                    System.out.println(state.getStateAbbreviation() + "   " + state.getStateName());
                }
            } else {
                State result = stateDao.getStateByAbbreviation(response.toUpperCase());
                if (result == null) {
                    displayError("Invalid state abbreviation.");
                } else {
                    return result;
                }
            }
        }
    }

    private City promptForCity() {
        List<City> cities = new ArrayList<>();
        while (cities.size() == 0) {
            System.out.println("What state is the city in?");
            State state = promptForState();
            cities = cityDao.getCitiesByState(state.getStateAbbreviation());
            if (cities.size() == 0) {
                displayError("No cities in selected state.");
            }
        }
        for (int i = 0; i < cities.size(); i++) {
            System.out.format("%3d. %s\n", i + 1, cities.get(i).getCityName());
        }
        while (true) {
            try {
                int selection = promptForInt("Please select a city: ");
                if (selection > cities.size() || selection < 1) {
                    displayError("Invalid selection.");
                } else {
                    int selectedCityId = cities.get(selection - 1).getCityId();
                    // the cities list *does* hold the full City objects, but
                    // this is for an example of calling the getCityById() method
                    return cityDao.getCityById(selectedCityId);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                displayError("Invalid selection.");
            }
        }
    }

    private Park promptForParkByName() {
        List<Park> parks = new ArrayList<>();


        while (parks.size() == 0) {
            System.out.println("What is the park's name?");
            String name = promptForName();
            boolean useWildCard = promptForWildCard();
            parks = parkDao.getParksByName(name, useWildCard);
            if (parks.size() == 0) {
                displayError("No parks found by name: " + name + ".");
            }
        }
        for (int i = 0; i < parks.size(); i++) {
            System.out.format("%3d. %s\n", i + 1, parks.get(i).getParkName());
        }
        while (true) {
            try {
                int selection = promptForInt("Please select a park: ");
                if (selection > parks.size() || selection < 1) {
                    displayError("Invalid selection.");
                } else {
                    int selectedParkId = parks.get(selection - 1).getParkId();
                    // the parks list *does* hold the full Park objects, but
                    // this is for an example of calling the getParkById() method
                    return parkDao.getParkById(selectedParkId);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                displayError("Invalid selection.");
            }
        }
    }

    private String promptForName() {
        String name = null;
        while (true)
        {
            System.out.print("Please enter all or part of the park's name you wish to search for: ");
            name = userInput.nextLine();
            if (name == null || name.trim().isEmpty())
            {
                displayError("Please enter a value to search for.");
            } else {
                return name;
            }
        }
    }

    private boolean promptForWildCard() {
        System.out.println("Is this the exact park name (y/n)?");
        boolean useWildCard = !userInput.nextLine().equalsIgnoreCase("y");
        return useWildCard;
    }

    private Park promptForParkByState() {
        List<Park> parks = new ArrayList<>();
        while (parks.size() == 0) {
            System.out.println("What state is the park in?");
            State state = promptForState();
            parks = parkDao.getParksByState(state.getStateAbbreviation());
            if (parks.size() == 0) {
                displayError("No parks in selected state.");
            }
        }
        for (int i = 0; i < parks.size(); i++) {
            System.out.format("%3d. %s\n", i + 1, parks.get(i).getParkName());
        }
        while (true) {
            try {
                int selection = promptForInt("Please select a park: ");
                if (selection > parks.size() || selection < 1) {
                    displayError("Invalid selection.");
                } else {
                    int selectedParkId = parks.get(selection - 1).getParkId();
                    // the parks list *does* hold the full Park objects, but
                    // this is for an example of calling the getParkById() method
                    return parkDao.getParkById(selectedParkId);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                displayError("Invalid selection.");
            }
        }
    }
}
