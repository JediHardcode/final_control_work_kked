package com.gmail.derynem.finalcontrolwork.service.model;

import com.gmail.derynem.finalcontrolwork.web.validation.ValidDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DiscountDTO { //TODO diff DTOs for show and save functionality
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private String percent;
    @NotNull
    @NotEmpty
    @ValidDate
    private String date;
    private UserDTO user = new UserDTO();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}