package com.ecommerce.ECommerce.model.dto;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.WebOrder;

public interface WebOrderDTO extends ListCrudRepository<WebOrder, Long> {

    List<WebOrder> findByUser(LocalUser user);
}
