package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.dto.StatusBody;
import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.service.OrdersService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "orders-management/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

        //so luong don hang theo status
        @PostMapping(value = "/total")
        public ResponseEntity<?> getOrdersByStatus(@RequestBody StatusBody statusBody){
            try {
                List<Object[]> status = ordersService.getOrdersByStatus(statusBody);
                if(status.isEmpty()){
                    return ResponseEntity.badRequest().body("không có dữ liệu");
                }
                return ResponseEntity.ok(status);
            }
            catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ : " + e.getMessage());}
        }

        //danh sach don hang theo shipper id
        @GetMapping (value= "/")
        public ResponseEntity<?> receiptsByStaffId(@RequestParam @Valid Integer shipperId){
            try {
                List<Orders> value = ordersService.findShipper(shipperId);
                if(value.isEmpty()){
                    return ResponseEntity.badRequest().body("không tìm thấy nhân viên này");
                }
                return ResponseEntity.ok(value);
            }
            catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ: " + e.getMessage());}

        }

        //tổng tien ship
        @PostMapping(value="/sum")
        public ResponseEntity<?> sumReport(@RequestBody ReceiptStaffBody receiptStaffBody){
                try {
                        List<Object[]> sum = ordersService.sumReport(receiptStaffBody);
                        if(sum.isEmpty()){
                                return ResponseEntity.badRequest().body("không có giá trị");
                        }
                        return ResponseEntity.ok().body(sum);
                } catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ: " + e.getMessage());}
        }

    //tong tien COD theo id shop
    @RestController
    public class OrderController {

        @GetMapping("/codbyshop/{shopId}")
        public ResponseEntity<?> getTotalCODByShopId(@PathVariable Integer shopId) {
            try {
                List<Orders> value = ordersService.getTotalCODByShopId(shopId);
                if (value.isEmpty()) {
                    return ResponseEntity.badRequest().body("không có dữ liệu");
                }
                return ResponseEntity.ok(value);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Lỗi máy chủ: " + e.getMessage());
            }
        }
    }


        //thong ke
    @GetMapping(value= "/data-by-month/")
    public ResponseEntity<?> dataByMonth(){
        try {
            List<Object[]> value = ordersService.getDataByMonth();
            if(value.isEmpty()){
                return ResponseEntity.badRequest().body("không có dữ liệu");
            }
            return ResponseEntity.ok(value);
        }
        catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ : " + e.getMessage());}
    }


    @GetMapping(value= "/data-by-id/")
    public ResponseEntity<?> dataById(){
        try {
            List<Object[]> value = ordersService.getDataById();
            if(value.isEmpty()){
                return ResponseEntity.badRequest().body("không có dữ liệu");
            }
            return ResponseEntity.ok(value);
        }
        catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ : " + e.getMessage());}
    }

}
