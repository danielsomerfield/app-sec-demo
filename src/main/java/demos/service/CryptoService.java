package demos.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CryptoService {

    public String hashPassword(final String password) {
        final MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes("UTF-8"));
            return toHex(m.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to hash password");
        }
    }

    private String toHex(final byte [] bytes) {
        final StringBuffer sb = new StringBuffer();
        for (final byte aByte : bytes) {
            if ((0xff & aByte) < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(0xff & aByte));
        }
        return sb.toString();
    }
}
