package com.example.demo.FileUtils;



import com.example.demo.model.Property;

import java.util.List;

public class QuickSort {

    public static void sortByPrice(List<Property> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<Property> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<Property> list, int low, int high) {
        double pivot = list.get(high).getPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getPrice() < pivot) {
                i++;
                Property temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Property temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i+1;
    }
}