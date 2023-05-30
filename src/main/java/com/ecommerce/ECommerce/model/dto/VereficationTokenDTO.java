package com.ecommerce.ECommerce.model.dto;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ECommerce.model.VerificationToken;

public interface VereficationTokenDTO extends ListCrudRepository<VerificationToken, Long> {

}
