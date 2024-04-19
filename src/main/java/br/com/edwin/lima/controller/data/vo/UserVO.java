package br.com.edwin.lima.controller.data.vo;

import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

public class UserVO extends RepresentationModel<UserVO> {

    private Long key;

    private String firstName;

    private String lastName;

    private String email;

    private String birthdayString;


    private String login;

    private String password;

    private String phone;

    private Date dateCreation;

    private Date dateLastLogin;
    
    private List<CarVO> cars;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Date dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    public List<CarVO> getCars() {
        return cars;
    }

    public void setCars(List<CarVO> cars) {
        this.cars = cars;
    }
}
