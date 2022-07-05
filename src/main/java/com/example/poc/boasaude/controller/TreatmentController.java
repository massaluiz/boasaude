package com.example.poc.boasaude.controller;


import com.example.poc.boasaude.model.Treatment;
import com.example.poc.boasaude.service.Interface.ITreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreatmentController {

    @Autowired
    ITreatment iTreatment;

    @GetMapping("/treatments")
    public List<Treatment> getAll() {
        return iTreatment.getAllTreatment();
    }

    @GetMapping("/treatment/{id}")
    public Treatment getTreatment(@PathVariable String id) {
        return iTreatment.getTreatment(id);
    }

    @PostMapping("/treatment")
    public Treatment addTreatment(@RequestBody Treatment treatment) {
        return iTreatment.addTreatment(treatment);
    }

    @DeleteMapping("/treatment/{id}")
    public void removeTreatment(@PathVariable String id) {
        iTreatment.removeTreatment(id);
    }
}
