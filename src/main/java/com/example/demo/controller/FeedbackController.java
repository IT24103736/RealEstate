//package com.example.demo.controller;
//
//import com.example.demo.model.Feedback;
//import com.example.demo.service.FeedbackService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin
//
//@RestController
//@RequestMapping("/feedbacks")
//public class FeedbackController {
//
//    @Autowired
//    private FeedbackService service;
//
//    @PostMapping
//    public String addFeedback(@RequestBody Feedback feedback) {
//        service.addFeedback(feedback);
//        return "Feedback submitted successfully!";
//    }
//
//    @GetMapping
//    public List<Feedback> getAllFeedbacks() {
//        return service.getAllFeedbacks();
//    }
//
//    @GetMapping("/sorted")
//    public List<Feedback> getSortedByRating() {
//        return service.getSortedByRating();
//    }
//}

package com.example.demo.controller;

import com.example.demo.model.Feedback;
import com.example.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @PostMapping
    public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback) {
        try {
            service.addFeedback(feedback);
            return ResponseEntity.ok("Feedback submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to submit feedback: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = service.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Feedback>> getSortedByRating() {
        List<Feedback> sorted = service.getSortedByRating();
        return ResponseEntity.ok(sorted);
    }


    // PUT method to update feedback by propertyId and userEmail (assuming these identify a feedback)
    @PutMapping("/{propertyId}/{userEmail}")
    public ResponseEntity<String> updateFeedback(@PathVariable String propertyId,
                                                 @PathVariable String userEmail,
                                                 @RequestBody Feedback updatedFeedback) {
        try {
            boolean updated = service.updateFeedback(propertyId, userEmail, updatedFeedback);
            if (updated) {
                return ResponseEntity.ok("Feedback updated successfully!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to update feedback: " + e.getMessage());
        }
    }

    // DELETE method to delete feedback by propertyId and userEmail
    @DeleteMapping("/{propertyId}/{userEmail}")
    public ResponseEntity<String> deleteFeedback(@PathVariable String propertyId,
                                                 @PathVariable String userEmail) {
        try {
            boolean deleted = service.deleteFeedback(propertyId, userEmail);
            if (deleted) {
                return ResponseEntity.ok("Feedback deleted successfully!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to delete feedback: " + e.getMessage());
        }
    }
}






