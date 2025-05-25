//package com.example.demo.service;
//
//
//
//import com.example.demo.FileUtils.PropertyBST;
//import com.example.demo.model.Property;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PropertyService {
//    private final PropertyBST bst = new PropertyBST();
//
//    public void addProperty(Property property) {
//        bst.insert(property);
//    }
//
//    public Property getProperty(String title) {
//        return bst.search(title);
//    }
//
//    public boolean updateProperty(String title, Property updated) {
//        Property existing = bst.search(title);
//        if (existing != null) {
//            bst.delete(title);
//            bst.insert(updated);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean deleteProperty(String title) {
//        Property existing = bst.search(title);
//        if (existing != null) {
//            bst.delete(title);
//            return true;
//        }
//        return false;
//    }
//
//    public List<Property> getAllSortedByTitle() {
//        return bst.inOrderTraversal();
//    }
//
//    public List<Property> getAllSortedByPrice() {
//        List<Property> list = bst.inOrderTraversal();
//        quickSort(list, 0, list.size() - 1);
//        return list;
//    }
//
//    private void quickSort(List<Property> list, int low, int high) {
//        if (low < high) {
//            int pi = partition(list, low, high);
//            quickSort(list, low, pi - 1);
//            quickSort(list, pi + 1, high);
//        }
//    }
//
//    private int partition(List<Property> list, int low, int high) {
//        double pivot = list.get(high).getPrice();
//        int i = low - 1;
//        for (int j = low; j < high; j++) {
//            if (list.get(j).getPrice() < pivot) {
//                i++;
//                Property temp = list.get(i);
//                list.set(i, list.get(j));
//                list.set(j, temp);
//            }
//        }
//        Property temp = list.get(i + 1);
//        list.set(i + 1, list.get(high));
//        list.set(high, temp);
//        return i + 1;
//    }
//}
//


package com.example.demo.service;

import com.example.demo.FileUtils.PropertyFileUtils;
import com.example.demo.model.Property;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PropertyService {

    public void addProperty(Property property) {
        PropertyFileUtils.appendProperty(property);
    }

    public Property getProperty(String title) {
        return PropertyFileUtils.readProperties().stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public boolean updateProperty(String title, Property updated) {
        List<Property> properties = PropertyFileUtils.readProperties();
        boolean found = false;

        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getTitle().equalsIgnoreCase(title)) {
                properties.set(i, updated);
                found = true;
                break;
            }
        }

        if (found) {
            PropertyFileUtils.writeProperties(properties);
        }
        return found;
    }

    public boolean deleteProperty(String title) {
        List<Property> properties = PropertyFileUtils.readProperties();
        boolean removed = properties.removeIf(p -> p.getTitle().equalsIgnoreCase(title));
        if (removed) {
            PropertyFileUtils.writeProperties(properties);
        }
        return removed;
    }

    public List<Property> getAllSortedByTitle() {
        List<Property> properties = PropertyFileUtils.readProperties();
        properties.sort(Comparator.comparing(Property::getTitle));
        return properties;
    }

    public List<Property> getAllSortedByPrice() {
        List<Property> properties = PropertyFileUtils.readProperties();
        properties.sort(Comparator.comparingDouble(Property::getPrice));
        return properties;
    }
}

