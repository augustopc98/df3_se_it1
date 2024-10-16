package com.example.demo.service;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.Discount;

import java.math.BigDecimal;

public interface CustomerOrderService {
    CustomerOrder placeCustomerOrder(CustomerOrder order);
    void cancelCustomerOrder(Long orderId);
    CustomerOrder updateCustomerOrder(Long orderId, CustomerOrder order);
    CustomerOrder getCustomerOrderDetails(Long orderId);
    BigDecimal calculateCustomerOrderTotal(Long orderId);
    void applyDiscountToCustomerOrder(Long orderId, Discount discount);
    void updateCustomerOrderDeliveryStatus(Long orderId, String status);
}
