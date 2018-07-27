package com.farehawker.farehawker;

public class Airport
{
    private String cityName;
    private String countryName;
    private String countryCode;
    private String airportCode;

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }
    public String getAirportCode() {
        return airportCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Airport()
    {

    }
}
