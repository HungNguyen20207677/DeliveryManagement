package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.dto.StatusBody;
import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.dto.ReceiptStaffBody;
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

    @GetMapping(value = "/")
    @PreAuthorize("hasAnyRole('MANAGER' , 'ADMIN')")
    public ResponseEntity<Page<Orders>> getAllReceipt(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "30") int size) {
            PageRequest pageable = PageRequest.of(page, size);
            Page<Orders> orders = ordersService.findAll(pageable);
            return ResponseEntity.ok(orders);
    }
        //so luong don hang theo status
        @PreAuthorize("hasAnyRole('MANAGER' , 'ADMIN')")
        @GetMapping(value= "/total?status={statusValue}")
        public ResponseEntity<?> ordersByStatus(@RequestBody StatusBody statusBody){
            try {
                List<Object[]> value = ordersService.getDataByMonth();
                if(value.isEmpty()){
                    return ResponseEntity.badRequest().body("không có dữ liệu");
                }
                return ResponseEntity.ok(value);
            }
            catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ : " + e.getMessage());}
        }

        //danh sach don hang theo shipper id
        @PreAuthorize("hasAnyRole('MANAGER' , 'ADMIN')")
        @PostMapping (value= "/{shipper_id}")
        public ResponseEntity<?> receiptsByStaffId(@PathVariable Integer shipper_id){
            try {
                List<Orders> staff = ordersService.findShipper(shipper_id);
                if(staff.isEmpty()){
                    return ResponseEntity.badRequest().body("không tìm thấy nhân viên này");
                }
                return ResponseEntity.ok(staff);
            }
            catch (Exception e) {return ResponseEntity.internalServerError().body("Lỗi máy chủ khi xóa phiếu thu: " + e.getMessage());}

        }

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

        //thong ke
    @PreAuthorize("hasAnyRole('MANAGER' , 'ADMIN')")
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

    @PreAuthorize("hasAnyRole('MANAGER' , 'ADMIN')")
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
