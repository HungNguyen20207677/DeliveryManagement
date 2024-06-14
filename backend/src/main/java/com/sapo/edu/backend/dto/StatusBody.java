package com.sapo.edu.backend.dto;

import com.sapo.edu.backend.model.enumclasses.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class StatusBody {
    private OrderStatus statusValue;

    public  OrderStatus getStatusValue(){
        return statusValue;
    }
}



