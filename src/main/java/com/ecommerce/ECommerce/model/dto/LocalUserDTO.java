package com.ecommerce.ECommerce.model.dto;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.ECommerce.model.LocalUser;

public interface LocalUserDTO extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUsername(String username);

    Optional<LocalUser> findByEmail(String email);
}
