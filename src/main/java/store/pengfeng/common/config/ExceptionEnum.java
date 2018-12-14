package store.pengfeng.common.config;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leon
 * Email: zoul@sce-re.com
 * Date: 2018-10-15
 */
@Getter
public enum ExceptionEnum {

    //
    EXCEPTION("EXCEPTION", "致命异常"),

    SQL_EXCEPTION("SQL_EXCEPTION", "SQL异常"),

    NULL_POINT_EXCEPTION("NULL_POINT_EXCEPTION", "空指针异常"),

    ILLEGAL_ARGUMENT_EXCEPTION("ILLEGAL_ARGUMENT_EXCEPTION", "非法参数异常"),

    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("METHOD_ARGUMENT_NOT_VALID_EXCEPTION", "方法参数非法异常");

    static Map<String, String> map = new HashMap<>();

    static {
        Arrays.stream(ExceptionEnum.values()).forEach(item -> map.put(item.code, item.name));
    }

    private String code;
    private String name;

    ExceptionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
