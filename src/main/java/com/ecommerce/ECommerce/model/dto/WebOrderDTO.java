package com.ecommerce.ECommerce.model.dto;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce.ECommerce.model.WebOrder;

public interface WebOrderDTO extends ListCrudRepository<WebOrder, Long> {

}
