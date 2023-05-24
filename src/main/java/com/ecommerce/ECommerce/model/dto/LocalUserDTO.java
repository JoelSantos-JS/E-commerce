package com.ecommerce.ECommerce.model.dto;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ECommerce.model.LocalUser;
import java.util.List;

@Repository
public interface LocalUserDTO extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUserName(String userName);

    Optional<LocalUser> findByEmail(String email);
}
