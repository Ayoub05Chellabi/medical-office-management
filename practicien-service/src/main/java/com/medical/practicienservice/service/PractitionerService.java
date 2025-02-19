package com.medical.practicienservice.service;

import com.medical.practicienservice.entity.Practitioner;
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

    // Récupérer tous les praticiens
    public List<Practitioner> getAllPractitioners() {
        return practitioners;
    }

    // Récupérer un praticien par ID avec Retry et Circuit Breaker
    @CircuitBreaker(name = "practitionerService", fallbackMethod = "fallbackGetPractitionerById")
    @Retry(name = "practitionerRetry", fallbackMethod = "fallbackGetPractitionerById")
    public Optional<Practitioner> getPractitionerById(Long id) {
        return practitioners.stream().filter(p -> p.getId().equals(id)).findFirst();
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

    // Méthode de secours (fallback) en cas de problème
    public Optional<Practitioner> fallbackGetPractitionerById(Long id, Throwable t) {
        System.out.println("Fallback activé pour getPractitionerById");
        return Optional.empty();
    }
}
