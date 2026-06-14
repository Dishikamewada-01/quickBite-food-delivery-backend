package com.tech.quickbite.dto.response;

public class RestaurantResponse {

    private Long id;

    private String name;

    private String address;

    private String city;

    private Double rating;

    public RestaurantResponse() {
    }

    public RestaurantResponse(
            Long id,
            String name,
            String address,
            String city,
            Double rating) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public Double getRating() {
        return rating;
    }
}