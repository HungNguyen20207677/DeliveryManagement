package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.repository.OrdersRepository;
import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.service.implement.OrdersServiceImpl;
import com.sapo.edu.backend.service.OrdersService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "orders-management/orders")
public class OrdersController {

        @Autowired
        private OrdersService ordersService;
        //danh sach don hang theo status

        //tong tien COD theo id shop

        //tổng tien ship
        @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
        @PostMapping(value="/sum")
        public ResponseEntity<?> sumReport(@RequestBody ReceiptStaffBody receiptStaffBody){
                try {
                        List<Object[]> sum = ordersService.sumReport(receiptStaffBody);
                        if(sum.isEmpty()){
                                return ResponseEntity.badRequest().body("không có giá trị");
                        }
                        return ResponseEntity.ok().body(sum);
                } catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ khi xóa phiếu giao hàng: " + e.getMessage());}
        }


}
