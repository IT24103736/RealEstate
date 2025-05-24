//package com.example.demo.model;
//
//import java.time.LocalDateTime;
//
//public class Feedback {
//    private String propertyId;
//    private String userEmail;
//    private String comment;
//    private int rating;
//    private LocalDateTime submittedAt;
//
//    public Feedback(String propertyId, String userEmail, String comment, int rating) {
//        this.propertyId = propertyId;
//        this.userEmail = userEmail;
//        this.comment = comment;
//        this.rating = rating;
//        this.submittedAt = LocalDateTime.now();
//    }
//
//
//    public String getPropertyId() {
//        return propertyId;
//    }
//    public String getUserEmail() {
//        return userEmail;
//    }
//    public String getComment() {
//        return comment;
//    }
//    public int getRating() {
//        return rating;
//    }
//    public LocalDateTime getSubmittedAt() {
//        return submittedAt;
//    }
//
//    @Override
//    public String toString() {
//        return "Feedback{" + "propertyId='" + propertyId + '\'' + ", userEmail='" + userEmail + '\'' + ", rating=" + rating + ", submittedAt=" + submittedAt + ", comment='" + comment + '\'' + '}';
//    }
//}
package com.example.demo.model;

import java.time.LocalDateTime;

public class Feedback {
    private String propertyId;
    private String userEmail;
    private String comment;
    private int rating;


    public Feedback(String propertyId, String userEmail, String comment, int rating) {
        this.propertyId = propertyId;
        this.userEmail = userEmail;
        this.comment = comment;
        this.rating = rating;

    }

    // No-args constructor for flexibility (optional but helpful)
    public Feedback() {}

    // Getters
    public String getPropertyId() {
        return propertyId;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getComment() {
        return comment;
    }
    public int getRating() {
        return rating;
    }


    // Setters
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Feedback{" +
                "propertyId='" + propertyId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", rating=" + rating +

                ", comment='" + comment + '\'' +
                '}';
    }
}
