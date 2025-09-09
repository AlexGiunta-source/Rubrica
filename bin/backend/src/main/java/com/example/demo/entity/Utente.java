package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 * Entità Utente. 
 * Contenente:
 * id identificativo dell'utente
 * email 
 * password 
 * ruolo per accedere agli endpoint
 * 
 * ogni utente è associato a molti contatti tramite relazione @OneToMany
 */
@Entity
@Table(name = "Utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;

    @Column
    String email;

    @Column
    String password;

    @Column
    String ruolo;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contatto> contatti = new ArrayList<>();

    public List<Contatto> getContatti() {
        return contatti;
    }

    public void setContatti(List<Contatto> contatti) {
        this.contatti = contatti;
    }

    public Utente() {
        super();
    }

    public Utente(Long id, String email, String password, String ruolo) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}