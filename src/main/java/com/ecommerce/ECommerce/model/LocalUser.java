package com.ecommerce.ECommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "local_user")
public class LocalUser {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 45, name = "user_name")
    private String userName;
    @Column(nullable = false, length = 25, name = "user_password")
    private String passWord;
    @Column(unique = true, nullable = false, length = 50, name = "user_email")
    private String email;
    @Column(nullable = false, length = 50, name = "user_first_name")
    private String firstName;
    @Column(nullable = false, length = 50, name = "user_last_name")
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
