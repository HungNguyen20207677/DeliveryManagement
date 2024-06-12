package com.sapo.edu.backend.dto;

import com.sapo.edu.backend.model.enumclasses.Roles;
import com.sapo.edu.backend.model.enumclasses.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UsersDTO {
    @Size(min = 1, max = 255)
    private String username;

    private String password;

    @Size(min = 1, max = 255)
    private String fullName;

    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @Size(min = 1, max = 20)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @Positive
    private double soTienDuNo;


    // Setter & getter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public double getSoTienDuNo() {
        return soTienDuNo;
    }

    public void setSoTienDuNo(double soTienDuNo) {
        this.soTienDuNo = soTienDuNo;
    }
}
