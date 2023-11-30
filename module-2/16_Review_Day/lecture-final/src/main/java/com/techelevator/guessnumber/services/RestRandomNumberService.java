package com.techelevator.guessnumber.services;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Component
public class RestRandomNumberService implements RandomNumberService {
    private static final List<MediaType> SUPPORTED_MEDIA_TYPES = List.of(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON);
    private static final String URL = "https://www.random.org/integers/?num=1&col=1&base=10&format=plain&rnd=new";

    private final RestTemplate restTemplate;

    public RestRandomNumberService() {
        restTemplate = restTemplateBuilder();
    }

    @Override
    public int getRandom(int lowerBound, int upperBound) {
        String configuredUrl = URL + "&min=" + lowerBound +"&max=" + upperBound;
        try {
            Integer result = restTemplate.getForObject(configuredUrl, Integer.class);
            if (result == null) {
                return generateMyOwn(lowerBound, upperBound);
            }
            return result;
        } catch (RestClientResponseException | ResourceAccessException e) {
            return generateMyOwn(lowerBound, upperBound);
        }
    }

    // make my own random
    private int generateMyOwn(int lowerBound, int upperBound) {
        return new Random().nextInt(upperBound - lowerBound) + lowerBound;
    }

    private RestTemplate restTemplateBuilder() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(SUPPORTED_MEDIA_TYPES);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(List.of(converter));
        return restTemplate;
    }
}
