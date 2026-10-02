package com.gym.crm.domain;

import java.time.LocalDate;
import java.util.Date;


public class Trainee extends User {
    private LocalDate dateOfBirth;
    private String address;
    private Long UserId;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }
}
