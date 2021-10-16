package xyz.sunnytoday.service.impl;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import xyz.sunnytoday.dto.ResponseMessage;

public class ResponseMessageTest {

    @Test
    @DisplayName("리스폰스 메세지 json 텍스트 확인")
    void responseMsgJsonTextCheck() {
        ResponseMessage responseMessage = new ResponseMessage(true, "Test");
        System.out.println(new Gson().toJson(responseMessage));
    }
}
