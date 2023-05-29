package com.ecommerce.ECommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.WebOrder;
import com.ecommerce.ECommerce.model.dto.WebOrderDTO;

@Service
public class OrderService {

    @Autowired
    private WebOrderDTO webOrderDTO;

    public List<WebOrder> getOrders(LocalUser user) {
        return webOrderDTO.findByUser(user);
    }

}
