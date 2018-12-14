package store.pengfeng.common.config;


/**
 * @auther: pengfeng
 * @date: 2018/12/12
 */
public class ExceptionResponse {

    private String code;

    private Object exceptionDetail;

    public ExceptionResponse(String code, Object exceptionDetail) {
        this.code = code;
        this.exceptionDetail = exceptionDetail;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "code='" + code + '\'' +
                ", message='" + exceptionDetail + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(Object exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
}
