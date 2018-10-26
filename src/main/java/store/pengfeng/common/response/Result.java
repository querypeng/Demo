package store.pengfeng.common.response;

/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */

public class Result<T> {

    private boolean success;

    private Error error;

    private T target;

    public Result() {
        super();
    }

    public Result(boolean success, Error error, T target) {
        super();
        this.success = success;
        this.error = error;
        this.target = target;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Error getError() {
        return error;
    }

    public Result<T> setError(Error error) {
        this.error = error;
        return this;
    }

    public T getTarget() {
        return target;
    }

    public Result<T> setTarget(T target) {
        this.target = target;
        return this;
    }

}
