package com.blockslicer.game.entity0;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gamercredentials")
public class GamerCredentialsWriteEntity {
    @Id
    @Column(columnDefinition = "TEXT")
    private String userId;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String password;

    private String role;

    public GamerCredentialsWriteEntity() {
    }

    public GamerCredentialsWriteEntity(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.role = "USER";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
