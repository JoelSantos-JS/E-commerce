package com.ecommerce.ECommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.WebOrder;
import com.ecommerce.ECommerce.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    private List<WebOrder> getOrdersB(@AuthenticationPrincipal LocalUser localUser) {
        return orderService.getOrders(localUser);
    }

}
