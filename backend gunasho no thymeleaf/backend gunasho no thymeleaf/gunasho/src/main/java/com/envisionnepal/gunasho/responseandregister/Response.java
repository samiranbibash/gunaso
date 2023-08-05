/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.responseandregister;

import com.envisionnepal.gunasho.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author User
 */
@Builder
@AllArgsConstructor
public class Response {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token ;
    private String message;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
    }

    public Response(String token, String message) {
        this.token = token;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" + "token=" + token + ", message=" + message + '}';
    }
}
