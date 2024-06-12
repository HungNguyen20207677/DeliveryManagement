package com.sapo.edu.backend.service;
import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.model.Orders;


import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;
@Service
public interface OrdersService {


    List<Orders> findSalesStaff(Integer shipper_id);
    List<Object[]> sumReport(ReceiptStaffBody receiptStaffBody);
    List<Object[]> getDataById();
    List<Object[]> getDataByMonth();
//    List<Object[]> getOrdersByPending();
//    List<Object[]> getOrdersByCanceled();
//    List<Object[]> getOrdersByShipping();
//    List<Object[]> getOrdersByAwaiting();
//    List<Object[]> getOrdersByCompleted();


}
