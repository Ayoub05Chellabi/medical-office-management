package com.medical.practitionerservice.service;

import com.medical.practitionerservice.entity.Practitioner;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PractitionerService {
    private final List<Practitioner> practitioners = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();


    public PractitionerService() {
        practitioners.add(new Practitioner(idCounter.getAndIncrement(), "Doe", "Neurologie", "john.doe@example.com", "000000000"));

    }
    // Récupérer tous les praticiens
    public List<Practitioner> getAllPractitioners() {
        return practitioners;
    }

//    // Récupérer un praticien par ID avec Retry et Circuit Breaker
//    @CircuitBreaker(name = "practitionerService", fallbackMethod = "fallbackGetPractitionerById")
//    @Retry(name = "practitionerRetry", fallbackMethod = "fallbackGetPractitionerById")
//    public Optional<Practitioner> getPractitionerById(Long id) {
//        return practitioners.stream().filter(p -> p.getId().equals(id)).findFirst();
//    }

    @CircuitBreaker(name = "practitionerService", fallbackMethod = "fallbackGetPractitionerById")
    @Retry(name = "practitionerRetry", fallbackMethod = "fallbackGetPractitionerById")
    public Optional<Practitioner> getPractitionerById(Long id) {
        System.out.println("Tentative de récupération du praticien ID: " + id);

        // Simuler une panne pour tester Resilience4j
        if (id == 99) {
            throw new RuntimeException("Échec forcé pour tester Resilience4j");

        }

        return practitioners.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    // Méthode de secours (Fallback)
    public Optional<Practitioner> fallbackGetPractitionerById(Long id, Throwable t) {
        System.out.println("⚠️ Fallback activé pour le praticien ID: " + id + " - Cause: " + t.getMessage());
        return Optional.of(new Practitioner(id, "Fallback", "Aucune spécialité", "fallback@example.com", "0000000000"));
    }

    // Ajouter un praticien
    public Practitioner addPractitioner(Practitioner practitioner) {
        practitioner.setId(idCounter.incrementAndGet());
        practitioners.add(practitioner);
        return practitioner;
    }

    // Supprimer un praticien
    public boolean deletePractitioner(Long id) {
        return practitioners.removeIf(p -> p.getId().equals(id));
    }

//    // Méthode de secours (fallback) en cas de problème
//    public Optional<Practitioner> fallbackGetPractitionerById(Long id, Throwable t) {
//        System.out.println("Fallback activé pour getPractitionerById");
//        return Optional.of(new Practitioner(id, "Fallback", "Aucune spécialité", "fallback@example.com", "0000000000"));
//    }
}
