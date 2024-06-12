package com.sapo.edu.backend.service.implement;

import com.sapo.edu.backend.dto.ReceiptStaffBody;
import com.sapo.edu.backend.dto.StatusBody;
import com.sapo.edu.backend.exception.ErrorException;
import com.sapo.edu.backend.repository.OrdersRepository;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

//    @Override
//    public Orders deleteOrderById(Integer orderId) {
//        Orders deleteOrder = ordersRepository.findOrderById(orderId);
//        if (deleteOrder != null) {
//            deleteOrder.setStatus(OrderStatus.CANCELED);
//        }
//
//        return ordersRepository.save(deleteOrder);
//    }
    // danh sach don hang
//    public Page<Orders> findAll(PageRequest pageable) {
//        Page<Orders> find = ordersRepository.findAll(pageable);
//        if (find.isEmpty()) {
//            throw new ErrorException("không tìm thấy đơn hàng");
//        }
//        return find;
//    }

    // danh sach don hang theo id shipper
    public List<Orders> findShipper(Integer shipper_id) {
        return ordersRepository.findByShipperId(shipper_id);
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
            default:
                throw new IllegalArgumentException("Giá trị không hợp lệ cho timeValue");
        }
    }

    public List<Object[]> findOrderByStatus(StatusBody statusBody) {
        if (statusBody == null) {
            throw new IllegalArgumentException("Không thể nhận giá trị null");
        }
        switch (statusBody.getStatusValue()) {

            case CANCELED:
                return ordersRepository.getOrdersByCanceled();
            case PENDING:
                return ordersRepository.getOrdersByPending();
            case SHIPPING:
                return ordersRepository.getOrdersByShipping();
            case AWAITING_PAYMENT:
                return ordersRepository.getOrdersByAwaiting();
            default:
                throw new IllegalArgumentException("Giá trị không hợp lệ cho timeValue");
        }
    }

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