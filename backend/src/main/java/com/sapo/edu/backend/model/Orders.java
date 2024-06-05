package com.sapo.edu.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapo.edu.backend.model.enumclasses.OrderStatus;
import com.sapo.edu.backend.model.enumclasses.ShipCostPaidBy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Users user;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "products_list")
    private Products productsList;

    @Column(name = "weight")
    private int weight;

    @Column(name = "COD")
    private double COD;

    @Column(name = "shipping_cost")
    private double shippingCost;

    @Column(name = "ship_cost_paid_by")
    @Enumerated(value = EnumType.STRING)
    ShipCostPaidBy shipCostPaidBy;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    OrderStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_checked_at", nullable = false, updatable = false)
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
