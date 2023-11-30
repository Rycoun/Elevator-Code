package com.techelevator;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                addCity();
            } else if (selection == 3) {
                viewParkInfo();
            } else if (selection == 4) {
                addPark();
            } else if (selection == 5) {
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
            System.out.println("1. View or modify a specific city");
            System.out.println("2. View a random city");
            System.out.println("3. View a list of city names");
            int selection = promptForInt("Please select an option: ");
            if (selection == 1) {
                City city = promptForCity();
                System.out.println();
                displayCity(city);
                promptToUpdateOrDeleteCity(city);
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
        try {
            cityCount = cityDao.getCityCount();
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());

        }
        return cityCount;
    }

    private City getRandomCity() {
        City city = null;
        try {
            city = cityDao.getRandomCity();
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
        return city;
    }

    private void displayCityNames() {
        System.out.println("The following cities are available: ");
        try {
            for (String cityName : cityDao.getCityNames()) {
                System.out.println(cityName);
            }
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
    }

    private void viewParkInfo() {
        int parkCount = getParkCount();
        System.out.println();
        System.out.println("There are " + parkCount + " parks available");
        System.out.println();
        if (parkCount > 0) {
            System.out.println("1. View or modify a specific park");
            System.out.println("2. View a random park");
            System.out.println("3. View a list of parks with camping");
            System.out.println("4. View a list of park names");
            int selection = promptForInt("Please select an option: ");
            if (selection == 1) {
                Park park = promptForPark();
                System.out.println();
                displayPark(park);
                promptToUpdateOrDeletePark(park);
            } else if (selection == 2) {
                System.out.println();
                Park park = getRandomPark();
                displayPark(park);
            } else if (selection == 3) {
                System.out.println();
                displayParksWithCamping();
            } else if (selection == 4) {
                System.out.println();
                displayParkNames();
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

    private Park getRandomPark() {
        Park park = null;
        park = parkDao.getRandomPark();
        return park;
    }

    private void displayParkNames() {
        System.out.println("The following parks are available: ");
        for (String parkName : parkDao.getParkNames()) {
            System.out.println(parkName);
        }
    }

    private void displayParksWithCamping() {
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
        System.out.println();
        System.out.println("1. View or modify city information");
        System.out.println("2. Add new city");
        System.out.println("3. View or modify park information");
        System.out.println("4. Add new park");
        System.out.println("5. Exit");
    }

    private void displayError(String message) {
        System.out.println();
        System.out.println("***" + message + "***");
    }

    private void displayCity(City city) {
        if (city != null) {
            System.out.println(city);
            System.out.format("Population: %,d Area: %,.1f sq. km\n", city.getPopulation(), city.getArea());
        } else {
            System.out.println("City is null");
        }
    }

    private void addCity() {
        City newCity = promptForNewCityData();
        try {
            newCity = cityDao.createCity(newCity);
            System.out.println("\nAdded the following city to the database:");
            displayCity(newCity);
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
    }

    private void updateCity(City cityToUpdate) {
        String newName = promptForString("New name (enter to leave unchanged): ");
        if (!newName.isBlank()) {
            cityToUpdate.setCityName(newName);
        }
        int newPopulation = promptForInt("New population (enter to leave unchanged): ");
        if (newPopulation >= 0) {
            cityToUpdate.setPopulation(newPopulation);
        }
        double newArea = promptForDouble("New area (enter to leave unchanged): ");
        if (newArea >= 0) {
            cityToUpdate.setArea(newArea);
        }

        try {
            City updatedCity = cityDao.updateCity(cityToUpdate);
            System.out.format("\nUpdated %s\n", updatedCity);
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
    }

    private void deleteCity(City cityToDelete) {
        State state = stateDao.getStateByCapital(cityToDelete.getCityId());
        if (state != null) {
            displayError(cityToDelete.getCityName() + " is the capital of "
                    + state.getStateName() + " and cannot be deleted.");
        } else {
            try {
                int deletedRows = cityDao.deleteCityById(cityToDelete.getCityId());
                if (deletedRows == 0) {
                    displayError("No records deleted.");
                } else {
                    System.out.format("\nDeleted %s\n", cityToDelete);
                }
            } catch (DaoException e) {
                displayError("Error occurred: " + e.getMessage());
            }
        }
    }

    private void displayPark(Park park) {
        if (park != null) {
            System.out.println(park);
            System.out.format("Established: %s Area: %,.1f sq. km\n", park.getDateEstablished(), park.getArea());
            System.out.format("This park %s camping.\n\n", park.getHasCamping() ? "offers" : "does not offer");
        } else {
            System.out.println("Park is null");
        }
    }

    private void addPark() {
        Park newPark = promptForNewParkData();

        try {
            newPark = parkDao.createPark(newPark);
            System.out.println("\nAdded the following park to the database:");
            displayPark(newPark);

            String[] states = promptForParkStates();
            List<String> addedToStates = addParkToStates(newPark, states);
            if (addedToStates.size() > 0) {
                System.out.format("Added %s to the following states:\n", newPark.getParkName());
                for (String state : addedToStates) {
                    System.out.println(" * " + state);
                }
            } else {
                displayError("Park not added to any states.");
            }
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
    }

    private List<String> addParkToStates(Park park, String[] stateAbbreviations) {
        List<String> addedToStates = new ArrayList<>();
        for (String stateAbbr : stateAbbreviations) {
            State state = stateDao.getStateByAbbreviation(stateAbbr);
            if (state != null) {
                parkDao.linkParkState(park.getParkId(), state.getStateAbbreviation());
                addedToStates.add(state.getStateAbbreviation());
            }
        }
        return addedToStates;
    }

    private void updatePark(Park parkToUpdate) {
        String newName = promptForString("New name (enter to leave unchanged): ");
        if (!newName.isBlank()) {
            parkToUpdate.setParkName(newName);
        }
        LocalDate newDateEstablished = promptForDate("New date established (YYYY-MM-DD or enter to leave unchanged): ");
        if (newDateEstablished != null) {
            parkToUpdate.setDateEstablished(newDateEstablished);
        }
        double newArea = promptForDouble("New area (enter to leave unchanged): ");
        if (newArea >= 0) {
            parkToUpdate.setArea(newArea);
        }
        String reply = promptForString("Does the park offer camping (Y/N or enter to leave unchanged)? ");
        if (reply.equalsIgnoreCase("Y")) {
            parkToUpdate.setHasCamping(true);
        } else if (reply.equalsIgnoreCase("N")) {
            parkToUpdate.setHasCamping(false);
        }

        try {
            Park updatedPark = parkDao.updatePark(parkToUpdate);
            System.out.format("\nUpdated %s\n", updatedPark);
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
    }

    private void deletePark(Park parkToDelete) {
        try {
            int deletedRows = parkDao.deleteParkById(parkToDelete.getParkId());
            if (deletedRows == 0) {
                displayError("No records deleted.");
            } else {
                System.out.format("\nDeleted %s\n\n", parkToDelete);
            }
        } catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
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

    private String promptForString(String prompt) {
        System.out.print(prompt);
        return userInput.nextLine();
    }

    private LocalDate promptForDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = userInput.nextLine();
            try {
                return LocalDate.parse(response);
            }  catch (DateTimeParseException e) {
                if (response.isBlank()) {
                    return null;
                } else {
                    displayError("Please format as YYYY-MM-DD.");
                }
            }
        }
    }

    private State promptForState() {
        while (true) {
            System.out.print("Please enter a state abbreviation (enter ? to list state abbreviations): ");
            String response = userInput.nextLine();
            if (response.equals("?")) {
                try {
                    for (State state : stateDao.getStates()) {
                        System.out.println(state.getStateAbbreviation() + "   " + state.getStateName());
                    }
                } catch (DaoException e) {
                    displayError("Error occurred: " + e.getMessage());
                }
            } else {
                try {
                    State result = stateDao.getStateByAbbreviation(response.toUpperCase());
                    if (result == null) {
                        displayError("Invalid state abbreviation.");
                    } else {
                        return result;
                    }
                } catch (DaoException e) {
                    displayError("Error occurred: " + e.getMessage());
                }
            }
        }
    }

    private City promptForCity() {
        List<City> cities = new ArrayList<>();
        while (cities.size() == 0) {
            System.out.println("What state is the city in?");
            State state = promptForState();
            try {
                cities = cityDao.getCitiesByState(state.getStateAbbreviation());
                if (cities.size() == 0) {
                    displayError("No cities in selected state.");
                }
            } catch (DaoException e) {
                displayError("Error occurred: " + e.getMessage());
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
                    // and having a second catch for DaoException
                    return cityDao.getCityById(selectedCityId);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                displayError("Invalid selection.");
            } catch (DaoException e) {
                displayError("Error occurred: " + e.getMessage());
            }
        }
    }

    private Park promptForPark() {
        List<Park> parks = new ArrayList<>();
        while (parks.size() == 0) {
            System.out.println("What state is the park in?");
            State state = promptForState();
            try {
                parks = parkDao.getParksByState(state.getStateAbbreviation());
                if (parks.size() == 0) {
                    displayError("No parks in selected state.");
                }
            } catch (DaoException e) {
                displayError("Error occurred: " + e.getMessage());
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
                    // and having a second catch for DaoException
                    return parkDao.getParkById(selectedParkId);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                displayError("Invalid selection.");
            } catch (DaoException e) {
                displayError("Error occurred: " + e.getMessage());
            }
        }
    }

    private void promptToUpdateOrDeleteCity(City city) {
        String response = promptForString("(U)pdate this city, (D)elete this city, or press Enter to continue: ");
        if (response.equalsIgnoreCase("U")) {
            updateCity(city);
        } else if (response.equalsIgnoreCase("D")) {
            deleteCity(city);
        }
    }

    private void promptToUpdateOrDeletePark(Park park) {
        String response = promptForString("(U)pdate this park, (D)elete this park, or press Enter to continue: ");
        if (response.equalsIgnoreCase("U")) {
            updatePark(park);
        } else if (response.equalsIgnoreCase("D")) {
            deletePark(park);
        }
    }

    private City promptForNewCityData() {
        City city = new City();

        String name = "";
        while (name.isBlank()) {
            name = promptForString("City name: ");
        }
        city.setCityName(name);
        int population = -1;
        while (population < 0) {
            population = promptForInt("Population: ");
        }
        city.setPopulation(population);
        double area = -1;
        while (area < 0) {
            area = promptForDouble("Area (in sq. km.): ");
        }
        city.setArea(area);

        System.out.println("What state is this city in?");
        State state = promptForState();
        city.setStateAbbreviation(state.getStateAbbreviation());

        return city;
    }

    private Park promptForNewParkData() {
        Park park = new Park();

        String name = "";
        while (name.isBlank()) {
            name = promptForString("Park name: ");
        }
        park.setParkName(name);
        LocalDate dateEstablished = null;
        while (dateEstablished == null) {
            dateEstablished = promptForDate("Date established (YYYY-MM-DD): ");
        }
        park.setDateEstablished(dateEstablished);
        double area = -1;
        while (area < 0) {
            area = promptForDouble("Area (in sq. km.): ");
        }
        park.setArea(area);
        String reply = promptForString("Does this park offer camping (Y/N)? ");
        park.setHasCamping(reply.equalsIgnoreCase("Y"));

        return park;
    }

    private String[] promptForParkStates() {
        String stateAbbreviations = "";
        while (stateAbbreviations.isBlank()) {
            stateAbbreviations = promptForString("List of abbreviations for the states this park is located in (separated by commas): ");
        }
        stateAbbreviations = stateAbbreviations.trim().toUpperCase();
        return stateAbbreviations.split(", *");
    }
}
