package com.example.fullstack.model;

import com.example.fullstack.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    private String contactPerson;

    private String drugLicense;

    private String gst;

    private String phoneNumber;

    private String password;

    private String role;

}
