package com.example.poc.boasaude.service.Interface;

import com.example.poc.boasaude.model.Treatment;

import java.util.List;

public interface ITreatment {

    List<Treatment> getAllTreatment();
    Treatment getTreatment(String id);

    Treatment addTreatment(Treatment treatment);

    void removeTreatment(String id);
}
