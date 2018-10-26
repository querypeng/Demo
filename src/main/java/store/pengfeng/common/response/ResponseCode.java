package store.pengfeng.common.response;

import java.util.HashMap;

/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */
public enum ResponseCode {
    //成功
    SUCCESS(200, "Success"),
    //失败
    FAIL(400, "Fail"),
    //未认证（签名错误）
    UNAUTHORIZED(401, "Unauthorized"),
    //参数错误
    PARAM_ERROR(402, "Parameter error"),
    //断言异常
    ASSERT_ERROR(403, "assert error"),
    //接口不存在
    NOT_FOUND(404, "Endpoint not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private static HashMap<Integer, ResponseCode> codeValueMap = new HashMap<>(7);

    static {
        for (ResponseCode type : ResponseCode.values()) {
            codeValueMap.put(type.code, type);
        }
    }

    private final int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCode getInstanceFromCode(int code) {
        return codeValueMap.get(code);
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
