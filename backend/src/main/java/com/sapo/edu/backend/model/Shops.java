package com.sapo.edu.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "shops")
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer shopId;

    @OneToMany(mappedBy = "shop")
    private List<Orders> orders = new ArrayList<Orders>();

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "so_tien_du_no")
    private double soTienDuNo = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_checked_at")
    private Date lastCheckedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    // Automatically assign the current date and time to createdAt fields before persisting
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
