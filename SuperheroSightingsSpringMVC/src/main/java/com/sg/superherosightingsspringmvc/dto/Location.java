/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dto;

import java.math.BigDecimal;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author tjsut
 */
public class Location {
    
    private int locationId;
    @NotEmpty(message = "You must include the name of the location.")
    @Length(max = 45, message = "Name must be no more than 45 characters in length.")
    private String name;
    @Length(max = 100, message = "Location description must be no more than 100 characters in length.")
    private String description;
    @NotEmpty(message = "You must include the building number and street name.")
    @Length(max = 40, message = "Street must be no more than 40 characters in length.")
    private String street;
    @NotEmpty(message = "You must include the name of the city.")
    @Length(max = 40, message = "City name must be no more than 40 characters in length.")
    private String city;
    @NotEmpty(message = "You must include the name of the state.")
    @Length(max = 20, message = "State must be no more than 20 characters in length.")
    private String state;
    @NotEmpty(message = "You must include the zip code.")
    @Length(max = 15, message = "Zip code must be no more than 15 characters in length.")
    private String zip;
    @NotEmpty(message = "You must include the latitude coordinate of the location.")
    @Length(max = 11, message = "Latitude must be no more than 9 characters in length (ex. 44.198288).")
    private BigDecimal latitude;
    @NotEmpty(message = "You must include the longitude coordinate of the location.")
    @Length(max = 11, message = "Longitude must be no more than 9 characters in length (ex. 44.198288).")
    private BigDecimal longitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.locationId;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.street);
        hash = 79 * hash + Objects.hashCode(this.city);
        hash = 79 * hash + Objects.hashCode(this.state);
        hash = 79 * hash + Objects.hashCode(this.zip);
        hash = 79 * hash + Objects.hashCode(this.latitude);
        hash = 79 * hash + Objects.hashCode(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }
   
}
