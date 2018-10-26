package store.pengfeng.common.response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */

public enum Error {
    //
    SUCCESS(200, "SUCCESS"),
    OPERATION_EXCEPTION(-1, "操作异常"),
    ILLEGAL_ID(42025001, "ID不存在"),
    ILLEGAL_CITY_NAME(42025002, "城市名称不存在"),
    FUNLIVE_RPC_SERVICE_EXCEPTION(65025003, "FUNLIVE远程服务调用异常");


    private static Map<Integer, Error> codeValueMap = new HashMap<>(100);

    static {
        Error[] var0 = values();

        for (Error type : var0) {
            codeValueMap.put(type.code, type);
        }

    }

    private int code;
    private String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error getInstanceFromCode(int code) {
        return codeValueMap.get(code);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
