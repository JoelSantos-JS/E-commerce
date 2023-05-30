package com.ecommerce.ECommerce.model.dto;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.VerificationToken;

public interface VereficationTokenDTO extends ListCrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    void deleteByUser(LocalUser user);
}
