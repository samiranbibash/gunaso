package com.envisionnepal.gunasho.responseandregister;

import org.springframework.stereotype.Component;

@Component
public class ChangePasswordRequest {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;


    public ChangePasswordRequest(String oldPassword, String newPassword, String confirmPassword) {
        super();
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }


    public ChangePasswordRequest() {

    }


    @Override
    public String toString() {
        return "ChangePasswordRequest [oldPassword=" + oldPassword + ", newPassword=" + newPassword
                + ", confirmPassword=" + confirmPassword + "]";
    }


    public String getOldPassword() {
        return oldPassword;
    }


    public String getNewPassword() {
        return newPassword;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }


    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }




}