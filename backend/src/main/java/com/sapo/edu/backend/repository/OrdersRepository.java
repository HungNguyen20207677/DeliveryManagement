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

    Orders findOrdersByStatus(String status);

    @Query("SELECT r, r.shippingCost FROM Orders r WHERE r.status = 'COMPLETED' AND DAY(r.createdAt) = DAY(:date) ")
    List<Object[]> getTotalMoneyByDay(Date date);

    @Query("SELECT r , r.shippingCost FROM Orders r WHERE r.status = 'COMPLETED' AND YEAR(r.createdAt) = YEAR(:date) AND WEEK(r.createdAt) = WEEK(:date)")
    List<Object[]> getTotalMoneyByWeek(Date date);

    @Query("SELECT r, r.shippingCost FROM Orders r WHERE r.status = 'COMPLETED' AND MONTH(r.createdAt) = MONTH(:date) ")
    List<Object[]> getTotalMoneyByMonth(Date date);

    // Tìm phiếu giao hang theo shipperID
//    @Query("SELECT r FROM Orders r WHERE r.shipper_id.user_id = :shipper_id ")
//    List<Orders> findByShipperId( Integer staffId);


    //tinh tong tien theo nhan vien ship moc thoi gian
//    @Query("SELECT r.salesStaff, SUM(r.moneyValue) FROM Receipt r WHERE r.status = 'ACTIVE' AND DAY(r.createDate) = DAY(:date) GROUP BY r.salesStaff")
//    List<Object[]> sumBySalesStaffByDay(Date date);
//    @Query("SELECT r.salesStaff, SUM(r.moneyValue) FROM Receipt r WHERE r.status = 'ACTIVE' AND YEAR(r.createDate) = YEAR(:date) AND WEEK(r.createDate) = WEEK(:date) GROUP BY r.salesStaff")
//    List<Object[]> sumBySalesStaffByWeek(Date date);
//    @Query("SELECT r.salesStaff, SUM(r.moneyValue) FROM Receipt r WHERE r.status = 'ACTIVE' AND YEAR(r.createDate) = YEAR(:date) AND MONTH(r.createDate) = MONTH(:date) GROUP BY r.salesStaff")
//    List<Object[]> sumBySalesStaffByMonth(Date date);

    // Biểu đồ
    @Query("SELECT YEAR(r.createdAt) AS Year, MONTH(r.createdAt) AS Month, SUM(r.shippingCost) AS TotalMoneyValue FROM Orders r WHERE r.status = 'COMPLETED' GROUP BY YEAR(r.createdAt), MONTH(r.createdAt) ORDER BY YEAR(r.createdAt), MONTH(r.createdAt)")
    List<Object[]> getDataByMonth();

    @Query("SELECT YEAR(r.createdAt) AS Year, MONTH(r.createdAt) AS Month, COUNT (r.id) AS CountIdValue FROM Orders r WHERE r.status = 'COMPLETED' GROUP BY YEAR(r.createdAt), MONTH(r.createdAt) ORDER BY YEAR(r.createdAt), MONTH(r.createdAt)")
    List<Object[]> getDataById();
}
