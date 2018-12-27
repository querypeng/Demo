package store.pengfeng.domain;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <pre>
 * Description
 * Copyright:	Copyright (c)2018
 * Author:		shishi
 * Created at:	2018/12/27 16:09
 * </pre>
 */
@Entity
@Table(name = "ss_user")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "updated_time", nullable = false)
    private Date updatedTime;

    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public User setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public User setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }
}
