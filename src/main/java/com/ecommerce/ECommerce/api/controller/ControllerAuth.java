package com.ecommerce.ECommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ECommerce.api.model.LoginBody;
import com.ecommerce.ECommerce.api.model.LoginResponse;
import com.ecommerce.ECommerce.api.model.RegistrationBody;
import com.ecommerce.ECommerce.exception.EmailFailed;
import com.ecommerce.ECommerce.exception.UserNotVerifiedException;
import com.ecommerce.ECommerce.model.LocalUser;
import com.ecommerce.ECommerce.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody)
            throws UserNotVerifiedException, EmailFailed {
        String jwt = null;

        try {
            jwt = userService.loginUser(loginBody);
        } catch (UserNotVerifiedException e) {
            // TODO: handle exception
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccess(false);
            String reason = "User not verified";
            if (e.isNewEmailSend()) {
                reason += " New email send";
            }
            loginResponse.setFailureReason(reason);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);
        } catch (EmailFailed ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();

            response.setJwt(jwt);
            response.setSuccess(true);

            return ResponseEntity.ok(response);
        }
    }

    @GetMapping(value = "/me")
    public LocalUser getLocalUser(@AuthenticationPrincipal LocalUser user) {
        return user;

    }

    @PostMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam String token) {
        if (userService.verifyUser(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
