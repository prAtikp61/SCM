package com.smart.smartcontact.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserForm {
    @NotBlank(message = "Username is Required")
    @Size(min = 3,max = 50 , message = "minimum 3 characters are required")
    private String name;
    @Email(message = "Invalid Email Address")
    private String email;
    @Size(min = 8,max = 12,message = "invalid mobile number")
    private String phoneNumber;
    @NotBlank(message = "password is Required")
    @Size(min = 6 ,message = "minimum 6 characters Required")
    private String password;
    private String about;

    // Manually add getters and setters (as a test)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
