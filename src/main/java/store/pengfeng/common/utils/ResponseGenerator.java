package store.pengfeng.common.utils;


import store.pengfeng.common.response.Response;
import store.pengfeng.common.response.ResponseCode;

/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */
public class ResponseGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "Success";

    public static <T> Response<T> success() {
        return new Response<T>()
                .setCode(ResponseCode.SUCCESS.code())
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>()
                .setCode(ResponseCode.SUCCESS.code())
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<T>()
                .setCode(ResponseCode.FAIL.code())
                .setMessage(message);
    }

    public static <T> Response<T> fail(int code, String message) {
        return new Response<T>()
                .setCode(code)
                .setMessage(message)
                .setData(null);
    }
}
