package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {
   @Autowired
    PatientRepository patientRepository;

   @GetMapping("/listPatients")
    public List<Patient> getAllPatients(){ return patientRepository.findAll();}

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable Integer id) {
        return patientRepository.findById(id).get();
    }

    @DeleteMapping("/patient/{id}")
    public List<Patient> deletePatient(@PathVariable Integer id) {
        patientRepository.delete(patientRepository.findById(id).get());
        return patientRepository.findAll();
    }

    @PostMapping("/patient")
    public List<Patient> addPatient(@RequestBody Patient patient) {
        patientRepository.save(patient);
        return patientRepository.findAll();
    }

    @PutMapping("/patient/{id}")
    public List<Patient> updatePatient(@RequestBody Patient patient, @PathVariable Integer id) {
        Patient patientObj = patientRepository.findById(id).get();
        patientObj.setName(patient.getName());
        patientObj.setAddress(patient.getAddress());
        patientObj.setAge(patient.getAge());
        patientRepository.save(patientObj);
        return patientRepository.findAll();
    }
}
