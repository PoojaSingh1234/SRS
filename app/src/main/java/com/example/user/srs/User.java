package com.example.user.srs;

/**
 * Created by USER on 13-03-2018.
 */

public class User {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String created_at;
    private String cnfPassword;
    private String city;
    private String adhar;

    private String token;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setCnfPassword(String cnfPassword) {
        this.cnfPassword = cnfPassword;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }




    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getAdhar() {
        return adhar;
    }
    public String getCreated_at() {
        return created_at;
    }



    public void setToken(String token) {
        this.token = token;
    }

}
