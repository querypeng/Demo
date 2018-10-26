package store.pengfeng.common.utils;

import store.pengfeng.common.response.Result;
import store.pengfeng.common.response.Error;

/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */

public class ResultGenerator {

    public static <T> Result<T> success() {
        return new Result<T>()
                .setSuccess(true)
                .setError(null)
                .setTarget(null);
    }

    public static <T> Result<T> success(T target) {
        return new Result<T>()
                .setSuccess(true)
                .setError(null)
                .setTarget(target);
    }

    public static <T> Result<T> success(Error error, T target) {
        return new Result<T>()
                .setSuccess(true)
                .setError(error)
                .setTarget(target);
    }

    public static <T> Result<T> fail(Error error) {
        return new Result<T>()
                .setSuccess(false)
                .setError(error)
                .setTarget(null);
    }

    public static <T> Result<T> fail(Error error, T target) {
        return new Result<T>()
                .setSuccess(false)
                .setError(error)
                .setTarget(target);
    }
}
