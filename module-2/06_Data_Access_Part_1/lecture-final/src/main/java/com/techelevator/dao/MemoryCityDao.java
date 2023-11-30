package com.techelevator.dao;

import com.techelevator.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemoryCityDao implements CityDao {
    private final List<City> cities = new ArrayList<>();

    public MemoryCityDao() {
        initializeCityData();
    }

    @Override
    public int getCityCount() {
        int count = 0;
        count = cities.size();
        return count;
    }

    @Override
    public int getMostPopulatedCity() {
        int highestPop = 0;
        for (City city : cities) {
            if (city.getPopulation() > highestPop) {
                highestPop = city.getPopulation();
            }
        }
        return highestPop;
    }

    @Override
    public int getLeastPopulatedCity() {
        int lowestPop = Integer.MAX_VALUE;
        for (City city : cities) {
            if (city.getPopulation() < lowestPop) {
                lowestPop = city.getPopulation();
            }
        }
        return lowestPop;
    }

    @Override
    public double getAverageCityArea() {
        double sumArea = 0.0;
        double avgArea = 0.0;
        for (City city : cities) {
            sumArea += city.getArea();
        }
        avgArea = sumArea / cities.size();
        return avgArea;
    }

    @Override
    public List<String> getCityNames() {
        List<String> cityNames = new ArrayList<>();
        for (City city : cities) {
            cityNames.add(city.getCityName());
        }
        return cityNames;
    }

    @Override
    public City getRandomCity() {
        City randomCity = null;
        Random rand = new Random();
        randomCity = cities.get(rand.nextInt(cities.size()));
        return randomCity;
    }

    @Override
    public City getCityById(int cityId) {
        for (City city : cities) {
            if (city.getCityId() == cityId) {
                return city;
            }
        }
        return null;
    }

    @Override
    public List<City> getCitiesByState(String stateAbbreviation) {
        List<City> returnCities = new ArrayList<>();
        for (City city : cities) {
            if (city.getStateAbbreviation().equalsIgnoreCase(stateAbbreviation)) {
                returnCities.add(city);
            }
        }
        return returnCities;
    }

    private void initializeCityData() {
        cities.add(new City(3, "Albany", "NY", 96460, 56.8));
        cities.add(new City(12, "Annapolis", "MD", 39223, 21.0));
        cities.add(new City(17, "Atlanta", "GA", 506811, 345.8));
        cities.add(new City(19, "Augusta", "ME", 18697, 150.3));
        cities.add(new City(22, "Austin", "TX", 978908, 809.9));
        cities.add(new City(25, "Baton Rouge", "LA", 220236, 222.5));
        cities.add(new City(33, "Bismarck", "ND", 73529, 90.1));
        cities.add(new City(34, "Boise", "ID", 228959, 212.6));
        cities.add(new City(35, "Boston", "MA", 692600, 125.1));
        cities.add(new City(49, "Carson City", "NV", 55916, 407.3));
        cities.add(new City(55, "Charleston", "WV", 46536, 84.5));
        cities.add(new City(57, "Charlotte Amalie", "VI", 18481, 3.1));
        cities.add(new City(60, "Cheyenne", "WY", 64235, 83.8));
        cities.add(new City(72, "Columbia", "SC", 131674, 345.8));
        cities.add(new City(74, "Columbus", "OH", 898553, 565.9));
        cities.add(new City(77, "Concord", "NH", 43627, 174.0));
        cities.add(new City(88, "Denver", "CO", 727211, 397.0));
        cities.add(new City(89, "Des Moines", "IA", 214237, 230.2));
        cities.add(new City(91, "Dover", "DE", 36047, 62.1));
        cities.add(new City(113, "Frankfort", "KY", 27755, 39.0));
        cities.add(new City(130, "Hagåtña", "GU", 1051, 2.6));
        cities.add(new City(132, "Harrisburg", "PA", 49528, 30.7));
        cities.add(new City(133, "Hartford", "CT", 122105, 45.1));
        cities.add(new City(135, "Helena", "MT", 33124, 43.7));
        cities.add(new City(141, "Honolulu", "HI", 345064, 156.7));
        cities.add(new City(146, "Indianapolis", "IN", 876384, 936.3));
        cities.add(new City(150, "Jackson", "MS", 160628, 287.5));
        cities.add(new City(152, "Jefferson City", "MO", 42708, 97.5));
        cities.add(new City(155, "Juneau", "AK", 31276, 8429.6));
        cities.add(new City(167, "Lansing", "MI", 118210, 101.3));
        cities.add(new City(174, "Lincoln", "NE", 289102, 238.5));
        cities.add(new City(175, "Little Rock", "AR", 197312, 307.4));
        cities.add(new City(182, "Madison", "WI", 259680, 199.4));
        cities.add(new City(198, "Montgomery", "AL", 198525, 413.9));
        cities.add(new City(199, "Montpelier", "VT", 7372, 26.5));
        cities.add(new City(204, "Nashville", "TN", 670820, 1232.6));
        cities.add(new City(218, "Oklahoma City", "OK", 655057, 1570.3));
        cities.add(new City(220, "Olympia", "WA", 52882, 52.0));
        cities.add(new City(227, "Pago Pago", "AS", 3656, 2.1));
        cities.add(new City(238, "Phoenix", "AZ", 1680992, 1340.6));
        cities.add(new City(239, "Pierre", "SD", 13867, 33.8));
        cities.add(new City(247, "Providence", "RI", 179883, 47.7));
        cities.add(new City(250, "Raleigh", "NC", 474069, 375.8));
        cities.add(new City(256, "Richmond", "VA", 230436, 154.9));
        cities.add(new City(264, "Sacramento", "CA", 513624, 253.6));
        cities.add(new City(265, "Saint Paul", "MN", 308096, 134.7));
        cities.add(new City(266, "Saipan", "MP", 47565, 118.9));
        cities.add(new City(267, "Salem", "OR", 174365, 125.9));
        cities.add(new City(269, "Salt Lake City", "UT", 200567, 288.0));
        cities.add(new City(276, "San Juan", "PR", 318441, 102.3));
        cities.add(new City(282, "Santa Fe", "NM", 84683, 135.6));
        cities.add(new City(297, "Springfield", "IL", 114230, 155.7));
        cities.add(new City(308, "Tallahassee", "FL", 194500, 260.0));
        cities.add(new City(315, "Topeka", "KS", 125310, 159.3));
        cities.add(new City(317, "Trenton", "NJ", 83203, 21.3));
        cities.add(new City(332, "Washington", "DC", 705749, 158.2));
    }
}
