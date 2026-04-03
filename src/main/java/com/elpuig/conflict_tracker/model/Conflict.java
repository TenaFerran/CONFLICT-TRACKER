package com.elpuig.conflict_tracker.model;

import com.elpuig.conflict_tracker.model.enums.ConflictStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="conflict")
public class Conflict {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private ConflictStatus status;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "conflict_country",
            joinColumns = @JoinColumn(name = "conflict_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;

    public Conflict() {
    }

    public Conflict(List<Country> countries, String description, ConflictStatus status, LocalDate startDate, String name, Long id, boolean deleted) {
        this.countries = countries;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.name = name;
        this.id = id;
        this.deleted = deleted;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public ConflictStatus getStatus() {
        return status;
    }

    public void setStatus(ConflictStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(List<Country> countries) {
        this.countries = countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}