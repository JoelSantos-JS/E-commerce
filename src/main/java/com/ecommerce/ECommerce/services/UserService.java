package com.ecommerce.ECommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ECommerce.api.model.LoginBody;
import com.ecommerce.ECommerce.api.model.RegistrationBody;
import com.ecommerce.ECommerce.exception.UserAlreadyExistException;
import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.model.dto.LocalUserDTO;

@Service
public class UserService {

    @Autowired
    private LocalUserDTO localUserDTO;

    @Autowired
    private EncryptionService es;

    @Autowired
    private JwtService jw;

    // Create a User
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException {
        if (localUserDTO.findByUserName(registrationBody.getUserName()).isPresent()
                || localUserDTO.findByEmail(registrationBody.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists or Email already exists");
        }

        LocalUser user = new LocalUser();

        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setEmail(registrationBody.getEmail());
        user.setUserName(registrationBody.getUserName());
        // Encryption Password
        user.setPassWord(es.encryptPassword(registrationBody.getPassword()));

        return localUserDTO.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> user = localUserDTO.findByUserName(loginBody.getUserName());

        if (user.isPresent()) {
            LocalUser user2 = user.get();
            if (es.verifyPassword(loginBody.getPassword(), user2.getPassWord())) {
                return jw.getAlgorithmJWT(user2);
            }
        }

        return null;
    }
}
