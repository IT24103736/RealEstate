package com.example.demo.FileUtils;


import com.example.demo.model.Property;

public class PropertyNode {
    public Property data;
    public PropertyNode left, right;

    public PropertyNode(Property data) {
        this.data = data;
        this.left = this.right = null;
    }
}
