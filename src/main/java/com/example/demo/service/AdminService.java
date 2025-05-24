package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Feedback;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private void rewriteFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Admin admin : admins) {
                String line = admin.getId() + "," + admin.getUsername() + "," + admin.getEmail() + "," +
                        admin.getPassword() + "," + admin.getLastLogin();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private final List<Admin> admins = new ArrayList<>();


    private static final String FILE_PATH = "admins.txt";
    private static long idCounter = getLastUsedId() + 1;

    public String addAdmin(Admin admin) {
        admin.setId(idCounter++);
        admin.setLastLogin(LocalDate.now());

        // Save to memory
        admins.add(admin);

        // Save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = admin.getId() + "," + admin.getUsername() + "," + admin.getEmail() + "," +
                    admin.getPassword() + "," + admin.getLastLogin();
            writer.write(line);
            writer.newLine();
            return "Admin added successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error saving admin to file.";
        }
    }


    // 🔍 Utility method to fetch last used ID
    private static long getLastUsedId() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return 0;

        long lastId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    try {
                        long id = Long.parseLong(parts[0]);
                        if (id > lastId) lastId = id;
                    } catch (NumberFormatException e) {
                        // Ignore invalid lines
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastId;
    }



    public List<Admin> getAllAdmins() {
        return admins;
    }

    public Admin getAdminById(Long id) {
        return admins.stream()
                .filter(admin -> admin.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String updateAdmin(Long id, Admin updatedAdmin) {
        Optional<Admin> optionalAdmin = admins.stream()
                .filter(admin -> admin.getId().equals(id))
                .findFirst();

        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            existingAdmin.setUsername(updatedAdmin.getUsername());
            existingAdmin.setEmail(updatedAdmin.getEmail());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            existingAdmin.setRole(updatedAdmin.getRole());
            existingAdmin.setLastLogin(LocalDate.now());
            return "Admin updated successfully!";
        } else {
            return "Admin not found!";
        }
    }

    public String deleteAdmin(Long id) {
        return admins.removeIf(admin -> admin.getId().equals(id))
                ? "Admin deleted successfully!"
                : "Admin not found!";
    }

    public Admin login(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                admin.setLastLogin(LocalDate.now());
                return admin;
            }
        }
        return null; // Invalid credentials
    }

}
