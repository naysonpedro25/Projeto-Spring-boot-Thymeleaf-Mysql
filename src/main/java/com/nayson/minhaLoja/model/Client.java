package com.nayson.minhaLoja.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column
    private String address;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;

    // Construtores, getters e setters

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String phone, String address, LocalDateTime createdTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;

        this.createdTime = createdTime;
    }

    // Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
