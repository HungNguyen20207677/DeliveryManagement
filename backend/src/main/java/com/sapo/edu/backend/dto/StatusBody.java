package com.sapo.edu.backend.dto;

import com.sapo.edu.backend.model.enumclasses.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class StatusBody {
    private OrderStatus statusValue;

}
