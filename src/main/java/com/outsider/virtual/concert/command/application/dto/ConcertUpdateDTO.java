package com.outsider.virtual.concert.command.application.dto;

public class ConcertUpdateDTO {
    private Long id;
    private String name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
