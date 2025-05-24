//package com.example.demo.FileUtils;
//
//import com.example.demo.model.Feedback;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FeedbackBST {
//    private FeedbackNode root;
//
//    public void insert(Feedback feedback) {
//        root = insertRec(root, feedback);
//    }
//
//    private FeedbackNode insertRec(FeedbackNode node, Feedback feedback) {
//        if (node == null) return new FeedbackNode(feedback);
//        if (feedback.getSubmittedAt().isBefore(node.data.getSubmittedAt())) {
//            node.left = insertRec(node.left, feedback);
//        } else {
//            node.right = insertRec(node.right, feedback);
//        }
//        return node;
//    }
//
//    public List<Feedback> inOrder() {
//        List<Feedback> list = new ArrayList<>();
//        inOrderRec(root, list);
//        return list;
//    }
//
//    private void inOrderRec(FeedbackNode node, List<Feedback> list) {
//        if (node != null) {
//            inOrderRec(node.left, list);
//            list.add(node.data);
//            inOrderRec(node.right, list);
//        }
//    }
//}
package com.example.demo.FileUtils;

import com.example.demo.model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackBST {
    private FeedbackNode root;

    public void insert(Feedback feedback) {
        root = insertRec(root, feedback);
    }

    // Insert without any sorting, always add to the right-most node
    private FeedbackNode insertRec(FeedbackNode node, Feedback feedback) {
        if (node == null) return new FeedbackNode(feedback);

        // Always insert to the right
        node.right = insertRec(node.right, feedback);
        return node;
    }

    public List<Feedback> inOrder() {
        List<Feedback> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(FeedbackNode node, List<Feedback> list) {
        if (node != null) {
            inOrderRec(node.left, list);
            list.add(node.data);
            inOrderRec(node.right, list);
        }
    }





}
