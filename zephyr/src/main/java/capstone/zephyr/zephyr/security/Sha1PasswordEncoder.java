package capstone.zephyr.zephyr.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Sha1PasswordEncoder implements PasswordEncoder {
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }

    private String encodeWithSalt(CharSequence rawPassword, String salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            throw new UnsupportedOperationException("SHA-1 is not available");
        }

        md.update(salt.getBytes());
        byte[] digest = md.digest(rawPassword.toString().getBytes());
        return bytesToHex(digest);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[4];
        secureRandom.nextBytes(salt);

        String hexSalt = bytesToHex(salt);
        return hexSalt + encodeWithSalt(rawPassword, hexSalt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String salt = encodedPassword.substring(0, 8);
        String hashedPart = encodedPassword.substring(8);

        return encodeWithSalt(rawPassword, salt).equals(hashedPart);
    }

}
