package com.pedium.auth.core.domain.entity;

import java.time.OffsetDateTime;

public class User {

    private String uid; //unique identifie, hash de cpf/cnpj
    private String name;
    private String password;
    private Role role;
    private Contact contact;
    private OffsetDateTime registrationDate;

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public OffsetDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(OffsetDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}