package com.qoonnect.rectem_api.model;

import com.qoonnect.rectem_api.security.PasswordHasher;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] profilePicture;
    private String username;
    private String password;

}
