package store.pengfeng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author pengfeng
 * @date 2018/10/27 0:39
 */
@Slf4j
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "store.pengfeng.dao")
public class CrmApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class,args);
	}
}
