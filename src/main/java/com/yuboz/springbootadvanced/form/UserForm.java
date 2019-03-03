package com.yuboz.springbootadvanced.form;

import com.yuboz.springbootadvanced.domain.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {

    public static final String PHONE_REX = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";

    @NotBlank
    private String username;
    @NotBlank
    @Length(min = 6,message = "At least 6 digits")
    private String password;
    @NotBlank
    private String confirmpassword;
    @Pattern(regexp = PHONE_REX, message = "Please use a correct phone number")
    private String phone;
    @Email
    private String email;

    public UserForm(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User convertToUser(){
        User user = new UserFormConvert().convert(this);
        return user;
    }


    private class UserFormConvert implements FormConvert<UserForm, User> {

        @Override
        public User convert(UserForm userForm) {
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            return user;
        }
    }



}
