package com.example.fullstack.model;

import com.example.fullstack.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    private String contactPerson;

    private String drugLicense;

    private String gst;

    private String phoneNumber;

    private UserStatus userStatus;

    private String password;

    private String role;


}
