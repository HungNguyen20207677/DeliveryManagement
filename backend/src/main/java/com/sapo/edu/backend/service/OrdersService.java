package com.sapo.edu.backend.service;
import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.model.Orders;


import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;
@Service
public interface OrdersService {
//    Orders getOrderstById(Integer id);

    Page<Orders> findAll(PageRequest pageable);
//    List<Orders> findSalesStaff(Integer staffId, ReceiptStaffBody receiptStaffBody);
//    List<Object[]> sumBySalesStaff(ReceiptStaffBody receiptStaffBody);
    List<Object[]> sumReport(ReceiptStaffBody receiptStaffBody);

}
