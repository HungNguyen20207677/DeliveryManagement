package com.sapo.edu.backend.service.implement;

import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.dto.StatusBody;
import com.sapo.edu.backend.exception.ErrorException;
import com.sapo.edu.backend.model.enumclasses.OrderStatus;
import com.sapo.edu.backend.repository.OrdersRepository;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private  OrdersRepository ordersRepository;
    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    // danh sach don hang theo id shipper
    public List<Orders> findShipper(Integer shipper_id) {
        return ordersRepository.findByShipperId(shipper_id);
    }

    public List<Orders> getTotalCODByShopId(Integer shopId) {
        return ordersRepository.getTotalCODByShopId(shopId);
    }

    public List<Object[]> sumReport(ReceiptStaffBody receiptStaffBody) {
        if (receiptStaffBody == null) {
            throw new IllegalArgumentException("Không thể nhận giá trị null");
        }
        Date currentDate = new Date();
        switch (receiptStaffBody.getTimeValue()) {
            case DAY:
                // Lọc theo ngày
                return ordersRepository.getTotalMoneyByDay(currentDate);
            case WEEK:
                // Lọc theo tuần
                return ordersRepository.getTotalMoneyByWeek(currentDate);
            case MONTH:
                // Lọc theo tháng
                return ordersRepository.getTotalMoneyByMonth(currentDate);
            case QUARTER:
                // Lọc theo tháng
                return ordersRepository.getTotalMoneyByQuarter(currentDate);
            case YEAR:
                // Lọc theo tháng
                return ordersRepository.getTotalMoneyByYear(currentDate);
            default:
                throw new IllegalArgumentException("Giá trị không hợp lệ cho timeValue");
        }
    }

    public List<Object[]> getOrdersByStatus(StatusBody statusBody) {
        if (statusBody == null) {
            throw new IllegalArgumentException("Không thể nhận giá trị null");
        }
        OrderStatus status = statusBody.getStatusValue();
        switch (status) {

            case CANCELED:
                return ordersRepository.getOrdersByCanceled();
            case PENDING:
                return ordersRepository.getOrdersByPending();
            case SHIPPING:
                return ordersRepository.getOrdersByShipping();
            case AWAITING_PAYMENT:
                return ordersRepository.getOrdersByAwaiting();
            case COMPLETED:
                return ordersRepository.getOrdersByCompleted();
            default:
                throw new IllegalArgumentException("Giá trị không hợp lệ cho timeValue");
        }
    }
//

    //thong ke
    public List<Object[]> getDataByMonth(){
        List<Object[]> value = ordersRepository.getDataByMonth();
        if(value.isEmpty()){
            throw new ErrorException("không có dữ liệu");
        }
        return (value);
    }
    public List<Object[]> getDataById(){
        List<Object[]> value = ordersRepository.getDataById();
        if(value.isEmpty()){
            throw new ErrorException("không có dữ liệu");
        }
        return (value);
    }




}