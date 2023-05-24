package com.ecommerce.ECommerce.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(name = "AddresLine1", nullable = false, length = 100)
    private String AddresLine1;
    @Column(name = "AddresLine2", nullable = false, length = 100)
    private String AddresLine2;
    @Column(name = "Country", nullable = false, length = 50)
    private String Country;
    @Column(name = "City", nullable = false, length = 50)
    private String city;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

    public long getId() {
        return id;
    }

    public String getAddresLine1() {
        return AddresLine1;
    }

    public String getAddresLine2() {
        return AddresLine2;
    }

    public String getCountry() {
        return Country;
    }

    public String getCity() {
        return city;
    }

    public LocalUser getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddresLine1(String addresLine1) {
        AddresLine1 = addresLine1;
    }

    public void setAddresLine2(String addresLine2) {
        AddresLine2 = addresLine2;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
