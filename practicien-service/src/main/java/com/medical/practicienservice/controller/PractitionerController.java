package com.medical.practicienservice.controller;


import com.medical.practicienservice.entity.Practitioner;
import com.medical.practicienservice.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/practiciens")
public class PractitionerController {
    @Autowired
    private PractitionerService practitionerService;

    @GetMapping
    public List<Practitioner> getAllPractitioners() {
        return practitionerService.getAllPractitioners();
    }

    @GetMapping("/{id}")
    public Optional<Practitioner> getPractitionerById(@PathVariable Long id) {
        return practitionerService.getPractitionerById(id);
    }

    @PostMapping
    public Practitioner addPractitioner(@RequestBody Practitioner practitioner) {
        return practitionerService.addPractitioner(practitioner);
    }

    @DeleteMapping("/{id}")
    public String deletePractitioner(@PathVariable Long id) {
        boolean removed = practitionerService.deletePractitioner(id);
        return removed ? "Praticien supprimé avec succès" : "Praticien non trouvé";
    }
}
