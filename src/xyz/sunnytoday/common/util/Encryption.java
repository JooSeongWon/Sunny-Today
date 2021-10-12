package xyz.sunnytoday.common.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * 패스워드 암호화 모듈
 */
public class Encryption {

    private static final int NUM_ITERATIONS = 10000;
    private static final int OUTPUT_KEY_LENGTH_IN_BYTES = 256 / 8;

    //salt 값을 얻습니다.
    public static String getSalt() {
        byte[] bytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        return Base64.getEncoder().encodeToString(bytes);
    }

    //패스워드와 salt를 넣어 비밀번호 암호화시에 널리쓰이는 PBKDF2WithHmacSHA256 알고리즘으로 암호화 합니다.
    public static String encodePassword(String password, String salt) {

        byte[] saltBytes = Base64.getDecoder().decode(salt);

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(),
                    saltBytes,
                    NUM_ITERATIONS,
                    OUTPUT_KEY_LENGTH_IN_BYTES * 8);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();

            byte[] outputBytes = new byte[16 + OUTPUT_KEY_LENGTH_IN_BYTES];

            System.arraycopy(saltBytes,
                    0,
                    outputBytes,
                    0,
                    16);
            System.arraycopy(hash, 0,
                    outputBytes,
                    16,
                    OUTPUT_KEY_LENGTH_IN_BYTES);

            return Base64.getEncoder().encodeToString(outputBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}