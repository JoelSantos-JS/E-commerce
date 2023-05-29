package com.ecommerce.ECommerce.model.dto;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommerce.ECommerce.model.VerificationToken;

public interface VereficationTokenDTO extends ListCrudRepository<VerificationToken, Long> {

}
