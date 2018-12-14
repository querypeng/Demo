package store.pengfeng.common.config;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常统一处理
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    protected ExceptionResponse handelUncaughtException(HttpServletRequest request, Exception exception) {
        String message = exception.getMessage();
        log.error(message, exception);

        JSONArray array = new JSONArray();
        Arrays.asList(exception.getStackTrace()).forEach(stackTraceElement ->
                array.add(stackTraceElement.getClassName() + "(" + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber() + ")"));

        if (exception instanceof BindException || exception instanceof MethodArgumentNotValidException) {
            return new ExceptionResponse(ExceptionEnum.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode(), array);
        }

        if (exception instanceof SQLException) {
            return new ExceptionResponse(ExceptionEnum.SQL_EXCEPTION.getCode(), array);
        }

        if (exception instanceof IllegalArgumentException) {
            return new ExceptionResponse(ExceptionEnum.ILLEGAL_ARGUMENT_EXCEPTION.getCode(), array);
        }

        if (exception instanceof NullPointerException) {
            return new ExceptionResponse(ExceptionEnum.NULL_POINT_EXCEPTION.getCode(), array);
        }
        return new ExceptionResponse(ExceptionEnum.EXCEPTION.getCode(), array);
    }
}
