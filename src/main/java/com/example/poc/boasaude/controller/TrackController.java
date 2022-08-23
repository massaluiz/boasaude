package com.example.poc.boasaude.controller;


import com.example.poc.boasaude.model.Track;
import com.example.poc.boasaude.service.Interface.ITreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackController {

    @Autowired
    ITreatment iTreatment;

    @GetMapping("/tracks")
    public List<Track> getAll() {
        return iTreatment.getAll();
    }
}
