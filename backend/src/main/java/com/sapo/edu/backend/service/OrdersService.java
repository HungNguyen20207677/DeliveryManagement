package com.sapo.edu.backend.service;
import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.model.Orders;


import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;
@Service
public interface OrdersService {

    Page<Orders> findAll(PageRequest pageable);

    List<Object[]> sumReport(ReceiptStaffBody receiptStaffBody);
    List<Object[]> getDataByMonth();
    List<Object[]> getDataById();

}
