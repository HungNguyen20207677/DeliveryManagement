package com.sapo.edu.backend.service;

import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.dto.StatusBody;
import com.sapo.edu.backend.model.Orders;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrdersService {
//    Orders deleteOrderById(Integer id);
//    Page<Orders> findAll(PageRequest pageable);
    List<Orders> findShipper(Integer shipper_id);

    List<Orders> getTotalCODByShopId(Integer shopId);

    List<Object[]> sumReport(ReceiptStaffBody receiptStaffBody);
    List<Object[]> getOrdersByStatus(StatusBody statusBody);
    List<Object[]> getDataById();
    List<Object[]> getDataByMonth();



}
