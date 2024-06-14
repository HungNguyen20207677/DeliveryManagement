package com.sapo.edu.backend.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShopsDTO {

    @Size(min = 1, max = 255)
    private String fullName;

    @Size(min = 1, max = 20)
    private String phone;

    @Positive
    private double soTienDuNo;

    // Getter & Setter
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSoTienDuNo() {
        return soTienDuNo;
    }

    public void setSoTienDuNo(double soTienDuNo) {
        this.soTienDuNo = soTienDuNo;
    }
}
