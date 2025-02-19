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


    public PatientService() {
        patients.add(new Patient(idCounter.getAndIncrement(), "Doe", "John", 30, "john.doe@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Smith", "Alice", 25, "alice.smith@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Johnson", "Robert", 40, "robert.johnson@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Brown", "Emily", 35, "emily.brown@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Davis", "Michael", 50, "michael.davis@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Miller", "Sophia", 29, "sophia.miller@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Wilson", "Daniel", 42, "daniel.wilson@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Moore", "Olivia", 37, "olivia.moore@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Taylor", "James", 45, "james.taylor@example.com"));
        patients.add(new Patient(idCounter.getAndIncrement(), "Anderson", "Emma", 28, "emma.anderson@example.com"));
    }

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