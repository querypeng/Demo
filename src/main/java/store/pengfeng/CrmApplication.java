package store.pengfeng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;

/**
 * @author pengfeng
 * @date 2018/10/27 0:39
 */
@Slf4j
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "store.pengfeng.dao")
public class CrmApplication {
    public static void main(String[] args) {
        SpringApplication newRun= new SpringApplication(CrmApplication.class);
        newRun.setBannerMode(Banner.Mode.CONSOLE);
        newRun.run(args);
    }

    /**
     * 配置上传文件大小的配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("102400KB");
        /// 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
