package com.ecommerce.ECommerce.model.dto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce.ECommerce.model.Product;

public interface ProductDTO extends ListCrudRepository<Product, Long> {

}
