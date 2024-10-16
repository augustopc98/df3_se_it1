package com.example.demo.controller;

import com.example.demo.domain.CustomerOrder;
import com.example.demo.domain.Discount;
import com.example.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/customerOrders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public ResponseEntity<CustomerOrder> placeCustomerOrder(@RequestBody CustomerOrder order) {
        return ResponseEntity.ok(customerOrderService.placeCustomerOrder(order));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelCustomerOrder(@PathVariable Long orderId) {
        customerOrderService.cancelCustomerOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<CustomerOrder> updateCustomerOrder(@PathVariable Long orderId, @RequestBody CustomerOrder order) {
        return ResponseEntity.ok(customerOrderService.updateCustomerOrder(orderId, order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CustomerOrder> getCustomerOrderDetails(@PathVariable Long orderId) {
        return ResponseEntity.ok(customerOrderService.getCustomerOrderDetails(orderId));
    }

    @GetMapping("/{orderId}/total")
    public ResponseEntity<BigDecimal> getCustomerOrderTotal(@PathVariable Long orderId) {
        return ResponseEntity.ok(customerOrderService.calculateCustomerOrderTotal(orderId));
    }

    @PostMapping("/{orderId}/discount")
    public ResponseEntity<Void> applyDiscountToCustomerOrder(@PathVariable Long orderId, @RequestBody Discount discount) {
        customerOrderService.applyDiscountToCustomerOrder(orderId, discount);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Void> updateCustomerOrderDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        customerOrderService.updateCustomerOrderDeliveryStatus(orderId, status);
        return ResponseEntity.noContent().build();
    }
}
