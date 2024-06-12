package com.sapo.edu.backend.repository;

import com.sapo.edu.backend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    Orders save(Orders orders);


    // tong tien ship theo moc thoi gian
    @Query("SELECT r, r.shippingCost FROM Orders r WHERE r.status = 'COMPLETED' AND DAY(r.createdAt) = DAY(:date) ")
    List<Object[]> getTotalMoneyByDay(Date date);
    @Query("SELECT r , r.shippingCost FROM Orders r WHERE r.status = 'COMPLETED' AND YEAR(r.createdAt) = YEAR(:date) AND WEEK(r.createdAt) = WEEK(:date)")
    List<Object[]> getTotalMoneyByWeek(Date date);
    @Query("SELECT r, r.shippingCost FROM Orders r WHERE r.status = 'COMPLETED' AND MONTH(r.createdAt) = MONTH(:date) ")
    List<Object[]> getTotalMoneyByMonth(Date date);

    // so luong don hang theo status
    @Query("SELECT COUNT(r.id) AS CountIdValue FROM Orders r WHERE r.status = 'PENDING'")
    List<Object[]> getOrdersByPending();
    @Query("SELECT COUNT(r.id) AS CountIdValue FROM Orders r WHERE r.status = 'CANCELED'")
    List<Object[]> getOrdersByCanceled();
    @Query("SELECT COUNT(r.id) AS CountIdValue FROM Orders r WHERE r.status = 'SHIPPING'")
    List<Object[]> getOrdersByShipping();
    @Query("SELECT COUNT(r.id) AS CountIdValue FROM Orders r WHERE r.status = 'AWAITING_PAYMENT'")
    List<Object[]> getOrdersByAwaiting();
    @Query("SELECT COUNT(r.id) AS CountIdValue FROM Orders r WHERE r.status = 'COMPLETED'")
    List<Object[]> getOrdersByCompleted();

    // danh sach don hang theo shipper_id
    @Query("SELECT r FROM Orders r WHERE r.user.id = :shipper_id ")
    List<Orders> findByShipperId( Integer shipper_id);

    // Biểu đồ
    @Query("SELECT YEAR(r.createdAt) AS Year, MONTH(r.createdAt) AS Month, SUM(r.shippingCost) AS TotalMoneyValue FROM Orders r WHERE r.status = 'COMPLETED' GROUP BY YEAR(r.createdAt), MONTH(r.createdAt) ORDER BY YEAR(r.createdAt), MONTH(r.createdAt)")
    List<Object[]> getDataByMonth();

    @Query("SELECT YEAR(r.createdAt) AS Year, MONTH(r.createdAt) AS Month, COUNT(r.id) AS CountIdValue FROM Orders r WHERE r.status = 'COMPLETED' GROUP BY YEAR(r.createdAt), MONTH(r.createdAt) ORDER BY YEAR(r.createdAt), MONTH(r.createdAt)")
    List<Object[]> getDataById();
}
