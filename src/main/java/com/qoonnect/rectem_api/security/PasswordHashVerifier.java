package com.qoonnect.rectem_api.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.qoonnect.rectem_api.util.VerificationStatus;

public class PasswordHashVerifier {

    public static boolean verifyPassword(String password, String hashedPassword) {
        System.out.println(password + " " + hashedPassword);
        return BCrypt.verifyer().verify(hashedPassword.toCharArray(),password.toCharArray()).verified;
    }
}
