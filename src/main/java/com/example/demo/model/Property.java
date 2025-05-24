//package com.example.demo.model;
//
//
//
//import java.time.LocalDate;
//
//
//public class Property {
//    private String title;
//    private String location;
//    private String email;
//    private boolean forSale;
//    private LocalDate postedDate;
//    private double price;
//
//    public Property() {
//        this.postedDate = LocalDate.now();
//    }
//
//    public Property(String title, String location, String email, boolean forSale) {
//        this.title = title;
//        this.location = location;
//        this.email = email;
//        this.forSale = forSale;
//        this.postedDate = LocalDate.now();
//    }
//// Create For Transaction  managment
//    public Property(String p001, String location, int i, boolean forSale) {
//    }
//
//    public Property(String part, String part1, double v, String part2) {
//    }
//
//
//    public String getTitle() {
//        return title;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public boolean isForSale() {
//        return forSale;
//    }
//    public void setForSale(boolean forSale) {
//        this.forSale = forSale;
//    }
//
//    public LocalDate getPostedDate() {
//        return postedDate;
//    }
//    public void setPostedDate(LocalDate postedDate) {
//        this.postedDate = postedDate;
//    }
//
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//
//}
//

package com.example.demo.model;

public class Property {
    private String title;
    private String location;
    private double price;
    private String description;

    // Constructors
    public Property() {}

    public Property(String title, String location, double price, String description) {
        this.title = title;
        this.location = location;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

