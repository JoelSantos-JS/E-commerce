package com.ecommerce.ECommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ECommerce.model.Product;
import com.ecommerce.ECommerce.model.dto.ProductDTO;

@Service
public class ProductService {

    @Autowired
    private ProductDTO productDTO;

    public List<Product> getAllProducts() {
        return productDTO.findAll();

    }

}
