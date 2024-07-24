package com.nayson.minhaLoja.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ClientDTO { // só pra filtrar os campos q são ditos pelo clients
    @NotEmpty(message = "O primeiro nome é obrigatório")
    @NotNull
    private String firstName;
    @NotEmpty(message = "O sobrenome é obrigatório")
    @NotNull
    private String lastName;
    @NotNull
    @NotEmpty(message = "O endereço de e-mail é obrigatório")
    @Email
    private String email;
    @NotNull
    @NotEmpty(message = "O número de telefone é obrigatório")
    private String phone;
    @NotNull
    @NotEmpty(message = "O endereço é obrigatório")
    private String address;
    public ClientDTO(){

    }

    public ClientDTO(String firstName, String lastName, String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
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
}
