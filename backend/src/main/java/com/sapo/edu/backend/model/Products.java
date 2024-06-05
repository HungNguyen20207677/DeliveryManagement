package com.sapo.edu.backend.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "count")
    private int count;
}
