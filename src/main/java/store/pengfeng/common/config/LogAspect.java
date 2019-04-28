package store.pengfeng.common.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * @author pengfeng
 * Email: pengfeng@rayootech.com
 */
@Aspect
@Component
@Order(1)
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* store.pengfeng.controller..*.*(..))")
    private void controllerAspect() {
    }


    @Before(value = "controllerAspect()")
    public void beforeHandle(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("=========================REQUEST CONTENT START=========================");
        logger.info("request url:{}", request.getRequestURL().toString());
        logger.info("request method:{}", request.getMethod());
        logger.info("request header:{}", JSONObject.toJSONString(getRequestHeaderMap(request)));
        Object[] argumentArray = removeResponseFromArguments(joinPoint.getArgs());
        logger.info("request param:{}", JSONObject.toJSONString(argumentArray));
        logger.info("=========================REQUEST CONTENT END===========================");
    }


    @AfterReturning(returning = "response", value = "controllerAspect()")
    public void afterHandle(Object response) {
        logger.info("=========================RESPONSE CONTENT START========================");
        logger.info("VO content is:{}", JSONObject.toJSONString(response));
        logger.info("=========================RESPONSE CONTENT END==========================");

    }


    private Map<String, String> getRequestHeaderMap(HttpServletRequest request) {
        Map<String, String> header = new HashMap<>(16);
        Enumeration enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String headerParam = (String) enums.nextElement();
            header.put(headerParam, request.getHeader(headerParam));
        }
        return header;
    }

    private Object[] removeResponseFromArguments(Object[] argumentArray) {
        if (ArrayUtils.isEmpty(argumentArray)) {
            return null;
        }
        for (Object obj : argumentArray) {
            if (obj instanceof HttpServletResponse) {
                argumentArray = ArrayUtils.removeElement(argumentArray, obj);
                break;
            }
        }
        return argumentArray;
    }

}
