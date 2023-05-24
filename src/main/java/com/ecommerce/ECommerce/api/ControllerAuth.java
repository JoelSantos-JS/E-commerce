package com.ecommerce.ECommerce.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ECommerce.api.model.RegistrationBody;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @PostMapping(value = "/register")
    public void registerUser(@RequestBody RegistrationBody registrationBody) {
        System.out.println("Register with success");
    }

}
