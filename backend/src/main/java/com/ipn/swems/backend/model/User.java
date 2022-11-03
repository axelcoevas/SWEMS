package com.ipn.swems.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author axel_
 */
@Document(collection = "users")
public class User {

    @Id
    private String userId;
    private String name;
    private Integer age;
    private String institution;
    private String occupation;
    private String email;
    private String password;

    public User() {
    }

    public User(String userId, String name, Integer age, String institution, String occupation, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.institution = institution;
        this.occupation = occupation;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
