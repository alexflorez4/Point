package main.java.com.ea.domain;

public class Address {

    private String street;
    private String street2;
    private String city;
    private String state;
    private String other;
    private String zipCode;
    private String country;

    public Address(String street, String street2, String city, String state, String other, String zipCode, String country) {
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.other = other;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return  street +
                "&street2=" + street2 +
                "&city=" + city +
                "&state=" + state +
                "&other=" + other +
                "&zipCode=" + zipCode +
                "&country=" + country;
    }
}
