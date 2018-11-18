package com.algaworks.algamoneyapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EntryFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDueBegin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDueUntil;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDueBegin() {
        return dateDueBegin;
    }

    public void setDateDueBegin(LocalDate dateDueBegin) {
        this.dateDueBegin = dateDueBegin;
    }

    public LocalDate getDateDueUntil() {
        return dateDueUntil;
    }

    public void setDateDueUntil(LocalDate dateDueUntil) {
        this.dateDueUntil = dateDueUntil;
    }
}
