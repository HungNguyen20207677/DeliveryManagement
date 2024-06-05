package com.sapo.edu.backend.model;

import com.sapo.edu.backend.model.enumclasses.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private Integer userId;

//    @Column(name = "orders_list")
//    private Orders ordersList;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @Column(name = "so_tien_du_no")
    private double soTienDuNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_checked_at", nullable = false)
    private Date lastCheckedAt;
}
