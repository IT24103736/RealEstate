package com.example.demo.FileUtils;



import com.example.demo.model.Property;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PropertyFileUtils {

    private static final String FILE_PATH = "properties.txt";

    public static List<Property> readProperties() {
        List<Property> properties = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return properties;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Property p = new Property(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
                    properties.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void writeProperties(List<Property> properties) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Property p : properties) {
                writer.write(String.join(",", p.getTitle(), p.getLocation(), String.valueOf(p.getPrice()), p.getDescription()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendProperty(Property property) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.join(",", property.getTitle(), property.getLocation(), String.valueOf(property.getPrice()), property.getDescription()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
