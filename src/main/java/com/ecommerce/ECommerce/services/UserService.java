package com.ecommerce.ECommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ECommerce.api.model.RegistrationBody;
import com.ecommerce.ECommerce.exception.UserAlreadyExistException;
import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.dto.LocalUserDTO;

@Service
public class UserService {

    @Autowired
    private LocalUserDTO localUserDTO;

    // Create a User
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException {
        if (localUserDTO.findByUserName(registrationBody.getUserName()).isPresent()
                && localUserDTO.findByEmail(registrationBody.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists or Email already exists");
        }

        LocalUser user = new LocalUser();

        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setEmail(registrationBody.getEmail());
        user.setUserName(registrationBody.getUserName());
        user.setPassWord(registrationBody.getPassword());

        return localUserDTO.save(user);
    }

    public void getUser() {
        localUserDTO.findAll();

    }
}
