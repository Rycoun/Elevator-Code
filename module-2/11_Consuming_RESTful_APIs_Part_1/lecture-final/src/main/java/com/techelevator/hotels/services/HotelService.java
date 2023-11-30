package com.techelevator.hotels.services;

import com.techelevator.hotels.model.City;
import com.techelevator.hotels.model.Hotel;
import com.techelevator.hotels.model.Review;
import org.springframework.web.client.RestTemplate;

public class HotelService {

    private static final String API_BASE_URL = "http://localhost:3000";
    private final RestTemplate restTemplate = new RestTemplate();

    public Hotel[] listHotels() {

        // http://localhost:3000/hotels

        String url = API_BASE_URL + "/hotels";

        Hotel[] results = restTemplate.getForObject(url, Hotel[].class);

        return results;
    }

    public Review[] listReviews() {
        String url = API_BASE_URL + "/reviews";

        return restTemplate.getForObject(url, Review[].class);
    }

    public Hotel getHotelById(int id) {
        String url = API_BASE_URL + "/hotels/" + id;

        return restTemplate.getForObject(url, Hotel.class);
    }

    public Review[] getReviewsByHotelId(int hotelId) {

//        String url = API_BASE_URL + "/reviews?hotelId=" + hotelId; // http://localhost:3000/reviews?hotelId=1
        String url = API_BASE_URL + "/hotels/" + hotelId + "/reviews"; // http://localhost:3000/hotels/1/reviews

        return restTemplate.getForObject(url, Review[].class);
    }

    public Hotel[] getHotelsByStarRating(int stars) {

        String url = API_BASE_URL + "/hotels?stars=" + stars; // http://localhost:3000/hotels?stars=4

        return restTemplate.getForObject(url, Hotel[].class);
    }

    public City getWithCustomQuery(){
        String url = "https://api.teleport.org/api/cities/geonameid:5206379";

        return restTemplate.getForObject(url, City.class);
    }

}
