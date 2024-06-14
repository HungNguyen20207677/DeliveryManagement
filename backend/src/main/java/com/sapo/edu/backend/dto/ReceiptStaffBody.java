package com.sapo.edu.backend.dto;

import com.sapo.edu.backend.model.enumclasses.ETime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ReceiptStaffBody {
    private ETime timeValue;

    public  ETime getTimeValue(){
        return timeValue;
    }
}
