package com.sapo.edu.backend.specification;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.model.Users;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class OrdersSpecification {

    public static Specification<Orders> hasCustomerName(String customerName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("customerName"), "%" + customerName + "%");
    }

    public static Specification<Orders> hasAddress(String address) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("address"), "%" + address + "%");
    }

    public static Specification<Orders> hasOrderId(Integer orderId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("orderId"), orderId);
    }

    public static Specification<Orders> hasUserName(String userName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.join("user").get("fullName"), "%" + userName + "%");
    }

    public static Specification<Orders> createdAfter(Date date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<Orders> createdBefore(Date date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), date);
    }
}
