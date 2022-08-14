package com.example.poc.boasaude.service;

import com.example.poc.boasaude.model.Treatment;
import com.example.poc.boasaude.service.Interface.ITreatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TreatmentService implements ITreatment {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Treatment> getAllTreatment() {
        String url = "http://localhost:8082/treatments";
        List<Treatment> treatments = new ArrayList<>();
        ResponseEntity<List> responseEntity = this.restTemplate.getForEntity(url, List.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            treatments = (List<Treatment>) responseEntity.getBody();
        }
        return treatments;
    }

    @Override
    public Treatment getTreatment(String id) {
        String url = "http://localhost:8082/treatment/" + id;
        Treatment treatment = new Treatment();
        ResponseEntity<Treatment> responseEntity = this.restTemplate.getForEntity(url, Treatment.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            treatment = responseEntity.getBody();
        }
        return treatment;
    }

    @Override
    public Treatment addTreatment(Treatment treatment) {
        String url = "http://localhost:8082/treatment/";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("title", treatment.getTitle());
        req_payload.put("description", treatment.getDescription());
        req_payload.put("createAt", LocalDateTime.now());
        req_payload.put("treatmentDate", LocalDateTime.of(
                Integer.parseInt(treatment.getTreatmentDate().substring(0,4)),
                Integer.parseInt(treatment.getTreatmentDate().substring(6,7)),
                Integer.parseInt(treatment.getTreatmentDate().substring(8,10)),
                0,
                0,
                0));
        req_payload.put("user", treatment.getUser());
        req_payload.put("speciality", treatment.getSpeciality());

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<Treatment> responseEntity = new RestTemplate().postForEntity(url, request, Treatment.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            treatment = responseEntity.getBody();
        }
        return treatment;
    }

    @Override
    public void removeTreatment(String id) {
        String url = "http://localhost:8082/treatment/" + id;
        this.restTemplate.delete(url);
    }

    @Override
    public List<Treatment> getTreatmentsByUser(String user) {
        String url = "http://localhost:8082/treatment/user/"+user;
        List<Treatment> treatments = new ArrayList<>();
        ResponseEntity<List> responseEntity = this.restTemplate.getForEntity(url, List.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            treatments = (List<Treatment>) responseEntity.getBody();
        }
        return treatments;
    }
}
