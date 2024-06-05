package com.sapo.edu.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
public class Products {
    private String productName;
    private int count;
}
