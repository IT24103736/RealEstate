////package com.example.demo.service;
////
////
////import com.example.demo.FileUtils.FeedbackBST;
////import com.example.demo.model.Feedback;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////public class FeedbackService {
////    private final FeedbackBST feedbackBST = new FeedbackBST();
////
////    public void addFeedback(Feedback feedback) {
////        feedbackBST.insert(feedback);
////    }
////
////    public List<Feedback> getAllFeedbacks() {
////        return feedbackBST.inOrder();
////    }
////
////    public List<Feedback> getSortedByRating() {
////        List<Feedback> list = feedbackBST.inOrder();
////        quickSort(list, 0, list.size() - 1);
////        return list;
////    }
////
////    private void quickSort(List<Feedback> list, int low, int high) {
////        if (low < high) {
////            int pi = partition(list, low, high);
////            quickSort(list, low, pi - 1);
////            quickSort(list, pi + 1, high);
////        }
////    }
////
////    private int partition(List<Feedback> list, int low, int high) {
////        int pivot = list.get(high).getRating();
////        int i = low - 1;
////        for (int j = low; j < high; j++) {
////            if (list.get(j).getRating() > pivot) {
////                i++;
////                Feedback temp = list.get(i);
////                list.set(i, list.get(j));
////                list.set(j, temp);
////            }
////        }
////        Feedback temp = list.get(i + 1);
////        list.set(i + 1, list.get(high));
////        list.set(high, temp);
////        return i + 1;
////    }
////}
//
//package com.example.demo.service;
//
//import com.example.demo.FileUtils.FeedbackBST;
//import com.example.demo.model.Feedback;
//import org.springframework.stereotype.Service;
//
//import java.io.*;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class FeedbackService {
//
//    private static final String FILE_PATH = "feedbacks.txt";
//    private final FeedbackBST feedbackBST = new FeedbackBST();
//
//    public FeedbackService() {
//        loadFeedbacksFromFile();
//    }
//
//    public void addFeedback(Feedback feedback) {
//        feedbackBST.insert(feedback);
//        rewriteFile();  // Save updated BST to file after insert
//    }
//
//    public List<Feedback> getAllFeedbacks() {
//        return feedbackBST.inOrder();
//    }
//
//    public List<Feedback> getSortedByRating() {
//        List<Feedback> list = feedbackBST.inOrder();
//        quickSort(list, 0, list.size() - 1);
//        return list;
//    }
//
//    private void quickSort(List<Feedback> list, int low, int high) {
//        if (low < high) {
//            int pi = partition(list, low, high);
//            quickSort(list, low, pi - 1);
//            quickSort(list, pi + 1, high);
//        }
//    }
//
//    private int partition(List<Feedback> list, int low, int high) {
//        int pivot = list.get(high).getRating();
//        int i = low - 1;
//        for (int j = low; j < high; j++) {
//            if (list.get(j).getRating() > pivot) {
//                i++;
//                Feedback temp = list.get(i);
//                list.set(i, list.get(j));
//                list.set(j, temp);
//            }
//        }
//        Feedback temp = list.get(i + 1);
//        list.set(i + 1, list.get(high));
//        list.set(high, temp);
//        return i + 1;
//    }
//
//    private void loadFeedbacksFromFile() {
//        File file = new File(FILE_PATH);
//        if (!file.exists()) return;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Format: propertyId,userEmail,comment,rating,submittedAt
//                String[] parts = line.split(",", 5);
//                if (parts.length < 5) continue;
//
//                try {
//                    Feedback feedback = new Feedback();
//                    feedback.setPropertyId(parts[0]);
//                    feedback.setUserEmail(parts[1]);
//                    feedback.setComment(parts[2]);
//                    feedback.setRating(Integer.parseInt(parts[3]));
//                    feedback.setSubmittedAt(LocalDateTime.parse(parts[4]));
//
//                    feedbackBST.insert(feedback);
//                } catch (Exception e) {
//                    e.printStackTrace(); // Skip malformed lines
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void rewriteFile() {
//        List<Feedback> feedbackList = feedbackBST.inOrder();
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            for (Feedback f : feedbackList) {
//                // Save as CSV: propertyId,userEmail,comment,rating,submittedAt
//                String line = f.getPropertyId() + "," +
//                        f.getUserEmail() + "," +
//                        f.getComment() + "," +
//                        f.getRating() + "," +
//                        f.getSubmittedAt();
//                writer.write(line);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


package com.example.demo.service;

import com.example.demo.FileUtils.FeedbackBST;
import com.example.demo.model.Feedback;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    private static final String FILE_PATH = "feedbacks.txt";
    private final FeedbackBST feedbackBST = new FeedbackBST();

    public FeedbackService() {
        loadFeedbacksFromFile();
    }

    public void addFeedback(Feedback feedback) {
        feedbackBST.insert(feedback);
        saveToFile(); // persist immediately
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackBST.inOrder();
    }

    public List<Feedback> getSortedByRating() {
        List<Feedback> list = feedbackBST.inOrder();
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private void quickSort(List<Feedback> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<Feedback> list, int low, int high) {
        int pivot = list.get(high).getRating();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getRating() > pivot) {
                i++;
                Feedback temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Feedback temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    private void loadFeedbacksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length < 5) continue;

                try {
                    Feedback feedback = new Feedback();
                    feedback.setPropertyId(parts[0]);
                    feedback.setUserEmail(parts[1]);
                    feedback.setComment(parts[2]);
                    feedback.setRating(Integer.parseInt(parts[3]));


                    feedbackBST.insert(feedback);
                } catch (Exception e) {
                    System.err.println("Invalid line, skipping: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load feedbacks from file.");
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        List<Feedback> feedbackList = feedbackBST.inOrder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Feedback f : feedbackList) {
                String line = String.join(",",
                        f.getPropertyId(),
                        f.getUserEmail(),
                        f.getComment().replace(",", " "), // prevent CSV breaking
                        String.valueOf(f.getRating())

                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write feedbacks to file.");
            e.printStackTrace();
        }
    }

    public boolean updateFeedback(String propertyId, String userEmail, Feedback updatedFeedback) {
        List<Feedback> allFeedbacks = feedbackBST.inOrder();
        boolean found = false;

        for (Feedback f : allFeedbacks) {
            if (f.getPropertyId().equals(propertyId) && f.getUserEmail().equals(userEmail)) {
                // Update the fields - you can update all or some fields as needed
                f.setComment(updatedFeedback.getComment());
                f.setRating(updatedFeedback.getRating());
//                f.setSubmittedAt(updatedFeedback.getSubmittedAt()); // optional

                found = true;
                break;
            }
        }

        if (found) {
            // Clear the BST and re-insert updated list (since your BST insert logic always inserts right, to keep order consistent)
            clearBST();
            for (Feedback f : allFeedbacks) {
                feedbackBST.insert(f);
            }
            saveToFile();
        }

        return found;
    }

    public boolean deleteFeedback(String propertyId, String userEmail) {
        List<Feedback> allFeedbacks = feedbackBST.inOrder();
        boolean found = false;

        // Remove feedback that matches propertyId and userEmail
        List<Feedback> updatedList = new ArrayList<>();
        for (Feedback f : allFeedbacks) {
            if (f.getPropertyId().equals(propertyId) && f.getUserEmail().equals(userEmail)) {
                found = true;
                continue; // skip adding to updatedList
            }
            updatedList.add(f);
        }

        if (found) {
            clearBST();
            for (Feedback f : updatedList) {
                feedbackBST.insert(f);
            }
            saveToFile();
        }

        return found;
    }

    // Helper to clear BST
    private void clearBST() {
        // Just reset root to null
        try {
            java.lang.reflect.Field rootField = FeedbackBST.class.getDeclaredField("root");
            rootField.setAccessible(true);
            rootField.set(feedbackBST, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
