package org.vaadin.jefferson.demo.simpleaddressbook.domain;

public class Contact {
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String COMPANY = "company";
    public static final String MOBILE_PHONE = "mobilePhone";
    public static final String WORK_PHONE = "workPhone";
    public static final String HOME_PHONE = "homePhone";
    public static final String WORK_EMAIL = "workEmail";
    public static final String HOME_EMAIL = "homeEmail";
    public static final String STREET = "street";
    public static final String ZIP = "zip";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String COUNTRY = "country";

    public static final String[] PROPERTIES = new String[] {
            FIRST_NAME, LAST_NAME, COMPANY, MOBILE_PHONE, WORK_PHONE,
            HOME_PHONE, WORK_EMAIL, HOME_EMAIL, STREET, ZIP, CITY, STATE,
            COUNTRY,
    };

    private String firstName = "John";
    private String lastName = "Doe";
    private String company = "Vaadin";
    private String mobilePhone = "";
    private String workPhone = "";
    private String homePhone = "";
    private String workEmail = "";
    private String homeEmail = "";
    private String street = "";
    private String zip = "";
    private String city = "";
    private String state = "";
    private String country = "";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getHomeEmail() {
        return homeEmail;
    }

    public void setHomeEmail(String homeEmail) {
        this.homeEmail = homeEmail;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
