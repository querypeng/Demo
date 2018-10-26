package store.pengfeng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 */
@RestController
public class HealthCheckController {

    @GetMapping(value = "/health")
    public String healthCheck() {
        return "I am OK ^_^!";
    }
}
