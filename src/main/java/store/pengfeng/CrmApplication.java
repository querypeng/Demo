package store.pengfeng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author pengfeng
 * @date 2018/10/27 0:39
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "store.pengfeng.dao")
public class CrmApplication {
    public static void main(String[] args) {
        SpringApplication newRun= new SpringApplication(CrmApplication.class);
        newRun.setBannerMode(Banner.Mode.CONSOLE);
        newRun.run(args);
    }
}
