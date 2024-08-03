package com.qoonnect.rectem_api.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.qoonnect.rectem_api.model.User;
import com.qoonnect.rectem_api.util.VerificationStatus;

import java.util.logging.Logger;

public class PasswordHasher {
    //instance class level variable to store the hashed password
    private String hashedPassword;
    private final String password;

    private final int workFactorOrCost;


    //This constructor is basically used to retrieve the password and workFactorOrCost, but the workFactorOrCost must be provided, not tightly coupled though.
    public PasswordHasher(String password, int workFactorOrCost) {
        this.password=password;
        this.workFactorOrCost=workFactorOrCost;
    }

    //This constructor is basically used to hash the password, while defining the cost or workFactorOrCost in a tightly coupled way
    public PasswordHasher(String password){
        this.password=password;
        this.workFactorOrCost=12;
    }
    private void hashPassword() {
        try {
            BCrypt.Hasher hasher = BCrypt.withDefaults();
            this.hashedPassword = hasher.hashToString(workFactorOrCost, password.toCharArray());
        } catch (Exception e) {
            // Handle exceptions gracefully
            throw new RuntimeException("Error hashing the password: " + e.getMessage(), e);
        }
    }


    public VerificationStatus verifyPassword(User user) {
        // this method does not implement the verification check out the QbPasswordVerifier.
        return null;
    }

    //Used to get the hashed password when ready to be stored in the database
    public String getHashedPasswordPass() {
        hashPassword();
        return hashedPassword;
    }
}
