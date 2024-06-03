package com.sapo.edu.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapo.edu.backend.model.enumclasses.OrderStatus;
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
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipper_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Shippers shipper;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Shops shop;

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
    private ShipCostPaidBy shipCostPaidBy;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

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
