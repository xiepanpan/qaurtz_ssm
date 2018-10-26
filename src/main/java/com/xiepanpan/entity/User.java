package com.xiepanpan.entity;

import lombok.Data;

/**
 * describe:
 *
 * @author xiepanpan
 * @date 2018/10/26
 */
@Data
public class User {

    private Integer id;

    private String userName;

    private String password;

    private Integer age;

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }
    public User() {
    }
}
