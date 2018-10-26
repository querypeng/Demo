package store.pengfeng.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import store.pengfeng.common.response.Response;
import store.pengfeng.common.response.ResponseCode;
import store.pengfeng.common.utils.ResponseGenerator;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数异常统一处理
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    protected Response<Void> handleMethodArgumentNotValidException(Exception e, WebRequest request) {

        // 异常信息
        List<ObjectError> allErrors;
        if (e instanceof BindException) {
            allErrors = ((BindException) e).getAllErrors();
        } else if (e instanceof MethodArgumentNotValidException) {
            allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        } else {
            allErrors = new ArrayList<>();
        }
        StringBuilder err = new StringBuilder();
        for (ObjectError objectError : allErrors) {
            err.append(objectError.getDefaultMessage());
            err.append(";");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseGenerator.fail(ResponseCode.PARAM_ERROR.code(), err.toString());
    }

    /**
     * 断言异常统一处理
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    protected Response handleIllegalArgumentException(RuntimeException e, WebRequest request) {
        IllegalArgumentException exception = (IllegalArgumentException) e;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String message = e.getMessage();
        logger.warn(message, e);
        return ResponseGenerator.fail(ResponseCode.ASSERT_ERROR.code(), message);
    }

    /**
     * 断言异常统一处理
     */
    @ExceptionHandler({IllegalStateException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    protected Response handleIllegalStateException(RuntimeException e, WebRequest request) {
        IllegalStateException exception = (IllegalStateException) e;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String message = e.getMessage();
        logger.warn(message, e);
        return ResponseGenerator.fail(ResponseCode.ASSERT_ERROR.code(), message);
    }

    /**
     * 统一异常处理
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    protected Response handleOtherException(Exception e, WebRequest request) {
        this.logErrorMessage(e);
        return ResponseGenerator.fail(ResponseCode.INTERNAL_SERVER_ERROR.code(), ResponseCode.INTERNAL_SERVER_ERROR.message());
    }

    private void logErrorMessage(Exception e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String message = e.getMessage();
        logger.error(message, e);
    }
}
