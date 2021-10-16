package xyz.sunnytoday.common.util;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Map;

/**
 * 암호화 모듈
 */
public class CipherUtil {

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
    public static String encodeSha256(String password, String salt) {

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

    //RSA 키 쌍 생성
    private static KeyPair genRSAKeyPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator gen;
        gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(1024, secureRandom);
        return gen.genKeyPair();
    }

    //파라미터를 복호화 해서 돌려준다.
    public static Map<String, String[]> getDecryptParams(HttpServletRequest request) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            PrivateKey privateKey = ((KeyPair) request.getSession().getAttribute("rsaKeyPair")).getPrivate();
            final Map<String, String[]> parameterMap = request.getParameterMap();

            for (String key : parameterMap.keySet()) {
                String[] paramArray = parameterMap.get(key);
                for (int i = 0; i < paramArray.length; i++) {
                    byte[] byteEncrypted = Base64.getDecoder().decode(paramArray[i].getBytes());
                    cipher.init(Cipher.DECRYPT_MODE, privateKey);
                    byte[] bytePlain = cipher.doFinal(byteEncrypted);
                    paramArray[i] = new String(bytePlain, StandardCharsets.UTF_8);
                }
            }

            return parameterMap;
        } catch (Exception e) {
            System.out.println("[ERROR] RSA 복호화 실패");
        }

        return null;
    }

    //공개키 평문 얻기 (Base64 인코딩)
    private static String getStringEncodedBase64(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    //세션에 키페어를 저장
    public static void checkKeyPair(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("rsaKeyPair") == null) {
            try {
                KeyPair keyPair = genRSAKeyPair();
                session.setAttribute("rsaKeyPair", keyPair);
                session.setAttribute("publicKey", getStringEncodedBase64(keyPair.getPublic()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

}