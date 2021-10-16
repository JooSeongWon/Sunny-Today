package xyz.sunnytoday.dto;

public class ResponseMessage {
    private final boolean result;
    private final String msg;

    public ResponseMessage(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public boolean getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }
}