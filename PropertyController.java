package com.example.demo.controller;



import com.example.demo.model.Property;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @PostMapping("/add")
    public String addProperty(@RequestBody Property property) {
        service.addProperty(property);
        return "Property added successfully!";
    }

    @GetMapping("/get/{title}")
    public Property getProperty(@PathVariable String title) {
        return service.getProperty(title);
    }

    @PutMapping("/update/{title}")
    public String updateProperty(@PathVariable String title, @RequestBody Property updated) {
        return service.updateProperty(title, updated) ? "Updated!" : "Property not found!";
    }

    @DeleteMapping("/delete/{title}")
    public String deleteProperty(@PathVariable String title) {
        return service.deleteProperty(title) ? "Deleted!" : "Property not found!";
    }

    @GetMapping("/all")
    public List<Property> getAllByTitle() {
        return service.getAllSortedByTitle();
    }

    @GetMapping("/sorted-by-price")
    public List<Property> getAllByPrice() {
        return service.getAllSortedByPrice();
    }
}




