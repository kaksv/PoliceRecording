package com.example.vian.policerecording;

public class Product {

    private String Title, Offender,NumberPlate, Description;

    public Product(){

    }

    public Product(String title, String offender, String numberPlate, String description) {
        Title = title;
        Offender = offender;
        NumberPlate = numberPlate;
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public String getOffender() {
        return Offender;
    }

    public String getNumberPlate() {
        return NumberPlate;
    }

    public String getDescription() {
        return Description;
    }
}
