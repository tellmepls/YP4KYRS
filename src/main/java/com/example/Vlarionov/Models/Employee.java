package com.example.Vlarionov.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100, message = "Поле должно содержать от 1 до 100 символов")
    private String surname;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100, message = "Поле должно содержать от 1 до 100 символов")
    private String name;
    private String middleName;
    @NotNull(message = "Поле не моет быть пустым")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9][0-9][0-9]", message = "Паспорт не соответсвует маске ввода")
    private String passport;
    @NotNull(message = "Поле не может быть пустым")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]", message = "Дата не соотвествует маске ввода")
    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String  birthday) {
        this.birthday = birthday;
    }

    public Employee() { }

    public Employee(String surname, String name, String middleName, String passport, String birthday) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.passport = passport;
        this.birthday = birthday;
    }
}
