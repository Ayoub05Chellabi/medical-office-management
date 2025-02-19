package com.medical.practitionerservice.entity;

public class Practitioner {
    private Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;

    // Constructeur
    public Practitioner(Long id, String nom, String specialite, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.email = email;
        this.telephone = telephone;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}
