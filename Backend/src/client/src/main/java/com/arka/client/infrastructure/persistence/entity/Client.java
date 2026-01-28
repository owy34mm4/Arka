package com.arka.client.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="clients")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "first_surname")
    private String firstSurname;

    @Column(name = "last_surname")
    private String lastSurname;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    
    @PrePersist  
    protected void onCreate() {  
        createdAt = LocalDateTime.now();  
    }
}
