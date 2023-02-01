package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity findDistilleriesThatHaveWhiskiesByAgeQueryString(
            @PathVariable Long id,
            @RequestParam(name = "age", required = false) Integer age) {
        if (age != null) {
            return new ResponseEntity<>(distilleryRepository.findByWhiskiesAge(age), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/distilleries")
    public ResponseEntity<Distillery> postDistillery(@RequestBody Distillery distillery){
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }


    @GetMapping(value = "/distilleries/whiskies")
    public ResponseEntity<List<Distillery>> findDistilleriesThatHaveWhiskiesByYearQueryString(
            @RequestParam(name="year") int year){
        return new ResponseEntity<>(distilleryRepository.findByWhiskiesYear(year), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> findDistilleriesFilterByRegion(
            @RequestParam(name = "region", required = false) String region) {
        if (region != null) {
            return new ResponseEntity<>(distilleryRepository.findDistilleryByRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }



}
