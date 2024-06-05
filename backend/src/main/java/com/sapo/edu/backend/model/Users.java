package com.sapo.edu.backend.model;

import com.sapo.edu.backend.model.enumclasses.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    Roles role;

    @Column(name = "phone")
    private String phone;


    @Temporal(TemporalType.DATE)
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    // Tự động gán thời điểm hiện tại
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }



}
