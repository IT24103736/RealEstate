package com.example.demo.FileUtils;



import com.example.demo.model.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyBST {
    private PropertyNode root;

    public void insert(Property property) {
        root = insertRec(root, property);
    }

    private PropertyNode insertRec(PropertyNode root, Property property) {
        if (root == null) return new PropertyNode(property);
        if (property.getTitle().compareToIgnoreCase(root.data.getTitle()) < 0)
            root.left = insertRec(root.left, property);
        else
            root.right = insertRec(root.right, property);
        return root;
    }

    public Property search(String title) {
        return searchRec(root, title);
    }

    private Property searchRec(PropertyNode root, String title) {
        if (root == null) return null;
        if (title.equalsIgnoreCase(root.data.getTitle())) return root.data;
        return title.compareToIgnoreCase(root.data.getTitle()) < 0
                ? searchRec(root.left, title)
                : searchRec(root.right, title);
    }

    public void delete(String title) {
        root = deleteRec(root, title);
    }

    private PropertyNode deleteRec(PropertyNode root, String title) {
        if (root == null) return null;

        if (title.compareToIgnoreCase(root.data.getTitle()) < 0) {
            root.left = deleteRec(root.left, title);
        } else if (title.compareToIgnoreCase(root.data.getTitle()) > 0) {
            root.right = deleteRec(root.right, title);
        } else {

            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            PropertyNode minLargerNode = getMin(root.right);
            root.data = minLargerNode.data;
            root.right = deleteRec(root.right, minLargerNode.data.getTitle());
        }
        return root;
    }

    private PropertyNode getMin(PropertyNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    public List<Property> inOrderTraversal() {
        List<Property> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(PropertyNode node, List<Property> list) {
        if (node != null) {
            inOrderRec(node.left, list);
            list.add(node.data);
            inOrderRec(node.right, list);
        }
    }
}

