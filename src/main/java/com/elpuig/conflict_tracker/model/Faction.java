package com.elpuig.conflict_tracker.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "faction")
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="conflict_id",nullable = false)
    private Conflict conflict;

    @ManyToMany
    @JoinTable(
            name = "faction_country",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;

    public Faction(Long id, String name, Conflict conflict, Country country) {
        this.id = id;
        this.name = name;
        this.conflict = conflict;
        this.countries = countries;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Conflict getConflict() {
        return conflict;
    }

    public void setConflict(Conflict conflict) {
        this.conflict = conflict;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
