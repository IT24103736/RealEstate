package com.example.demo.FileUtils;

import com.example.demo.model.Feedback;

public class FeedbackNode {
    Feedback data;
    FeedbackNode left, right;

    public FeedbackNode(Feedback data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
