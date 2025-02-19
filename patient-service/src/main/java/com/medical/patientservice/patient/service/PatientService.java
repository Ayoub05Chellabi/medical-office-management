package com.medical.patientservice.patient.service;

import com.medical.patientservice.patient.entity.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;


@Service
public class PatientService {
    private final List<Patient> patients = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    // Récupérer tous les patients
    public List<Patient> getAllPatients() {
        return patients;
    }

    // Récupérer un patient par ID
    public Optional<Patient> getPatientById(Long id) {
        return patients.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // Ajouter un patient
    public Patient addPatient(Patient patient) {
        patient.setId(idCounter.incrementAndGet());
        patients.add(patient);
        return patient;
    }

    // Supprimer un patient
    public boolean deletePatient(Long id) {
        return patients.removeIf(p -> p.getId().equals(id));
    }
}