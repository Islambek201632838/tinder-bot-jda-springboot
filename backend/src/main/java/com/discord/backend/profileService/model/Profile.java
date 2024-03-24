package com.discord.backend.profileService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {
    // Fields
    @Id
    private String id; // Discord ID as the primary key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String age;

    @Column(nullable = true)
    private String university;

    @Column(nullable = true)
    private String buddy;

    @Column(nullable = false)
    private String degree;

    @Column(nullable = false, length = 2048)
    private String aboutMe;



    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getUniversity() {
        return university;
    }

    public String getBuddy() {
        return buddy;
    }

    public String getDegree() {
        return degree;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setBuddy(String buddy) {
        this.buddy = buddy;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }


}
