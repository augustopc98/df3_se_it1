package com.example.demo.service;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.Discount;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    @Transactional
    public CustomerOrder placeCustomerOrder(CustomerOrder order) {
        return customerOrderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelCustomerOrder(Long orderId) {
        customerOrderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public CustomerOrder updateCustomerOrder(Long orderId, CustomerOrder order) {
        if (customerOrderRepository.existsById(orderId)) {
            order.setId(orderId);
            return customerOrderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public CustomerOrder getCustomerOrderDetails(Long orderId) {
        return customerOrderRepository.findById(orderId).orElse(null);
    }

    @Override
    public BigDecimal calculateCustomerOrderTotal(Long orderId) {
        CustomerOrder order = getCustomerOrderDetails(orderId);
        return order != null ? order.calculateTotal() : BigDecimal.ZERO;
    }

    @Override
    public void applyDiscountToCustomerOrder(Long orderId, Discount discount) {
        CustomerOrder order = getCustomerOrderDetails(orderId);
        if (order != null) {
            order.applyDiscount(discount);
            customerOrderRepository.save(order);
        }
    }

    @Override
    public void updateCustomerOrderDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = getCustomerOrderDetails(orderId);
        if (order != null) {
            order.updateDeliveryStatus(status);
            customerOrderRepository.save(order);
        }
    }
}
