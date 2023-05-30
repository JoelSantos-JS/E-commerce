package com.ecommerce.ECommerce.model.dto;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ECommerce.model.LocalUser;
import java.util.List;

public interface LocalUserDTO extends ListCrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUserName(String userName);

    Optional<LocalUser> findByEmail(String email);
}
