package com.ecommerce.ECommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ECommerce.api.model.RegistrationBody;
import com.ecommerce.ECommerce.services.UserService;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public void getRegistrationBody() {
        userService.getUser();
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // TODO: handle exception

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
