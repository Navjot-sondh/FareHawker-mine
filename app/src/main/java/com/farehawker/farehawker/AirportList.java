package com.farehawker.farehawker;

public class AirportList
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




    public AirportList(String cityName, String countryName, String countryCode, String airportCode)
    {
        this.airportCode=airportCode;
        this.cityName=cityName;
        this.countryCode=countryCode;
        this.countryName=countryName;
    }
}
