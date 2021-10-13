package sample;

import xyz.sunnytoday.common.util.Encryption;

public class EncryptionTest {
    
    //비밀번호 더미데이터 생성용 메인
    public static void main(String[] args) {
        //salt 얻기
        String salt = Encryption.getSalt();  //이값을 데이터베이스 salt에 넣으시면 됩니다.
        System.out.println("salt = " + salt);
        
        //비밀번호
        String password = "abc123";
        System.out.println("password = " + password);
        
        //솔트와 비밀번호를 가지고 해시얻기
        String passwordHash = Encryption.encodePassword(password, salt); //이값을 데이터베이스 password에 넣으시면 됩니다.
        System.out.println("passwordHash = " + passwordHash);
        
        /*
        * 로그인 구현 순서
        * 아이디 / 비밀번호를 받아옵니다.
        * 아이디를 통해 회원을 검색해서 가져옵니다. (비밀번호 일치여부 검사 X)
        * 
        * 해당 회원의 salt와 화면에서 받아온 비밀번호를 통해  
        * Encryption.encodePassword(password, salt); 를 수행하고 문자열을 얻습니다.
        * 데이터베이스에서 얻어온 password와 위에서 얻은 password가 일치하면 로그인 성공입니다.
        * 
        * 로그인이 성공하면 세션에 userno를 저장합니다.
        * 
        * 
        * 
        * 회원가입시 비밀번호 저장 순서
        * 
        * Encryption.getSalt(); 를 통해 유저의 salt를 만듭니다.
        * 유저의 비밀번호를  Encryption.encodePassword(password, salt); 통해 암호화하고
        * 위에서 얻은 salt와 암호화된 비밀번호를 각각 데이터베이스 salt, password에 저장합니다.
        * */
    }
}
