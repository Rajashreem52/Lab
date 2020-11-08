package com.example.demo.model;

/*
Author Rafi
*/

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @Column(name = "experience")
    private String experience;

    @Column(name = "status")
    private Status status;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "notes")
    private String notes;

    public static enum Status {
        PENDING, ACCEPTED, DECLINED, INVITED
    }

    public Applicant() {

    }

    public Applicant(String name, String email, String gender, int age, String password, String experience, Status status) {

        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.password = password;
        this.experience = experience;
        this.status = status;
        this.createdAt = new Date();
        this.notes = "";

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
