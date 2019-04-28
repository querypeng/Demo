package store.pengfeng.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private static final String CONFIGDIRVERCLASSNAME = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";

    @Value("${spring.datasource.driver-class-name:}")
    private String driverClassName;

    @Value("${spring.datasource.url:}")
    private String url;

    @Value("${spring.datasource.username:}")
    private String userName;

    @Value("${spring.datasource.password:}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(driverClassName);
        config.addDataSourceProperty("url", url);
        config.addDataSourceProperty("user", userName);
        config.addDataSourceProperty("password", password);

        // MySQL optimizations, see
        // https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
        if (CONFIGDIRVERCLASSNAME.equals(driverClassName)) {
            config.addDataSourceProperty("cachePrepStmts", true);
            config.addDataSourceProperty("prepStmtCacheSize", 250);
            config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
            /*config.addDataSourceProperty("useServerPrepStmts",true);
            config.addDataSourceProperty("useLocalSessionState",true);
            config.addDataSourceProperty("rewriteBatchedStatements",true);
            config.addDataSourceProperty("cacheResultSetMetadata",true);
            config.addDataSourceProperty("cacheServerConfiguration",true);
            config.addDataSourceProperty("elideSetAutoCommits",true);
            config.addDataSourceProperty("maintainTimeStats",false);*/
        }

        return new HikariDataSource(config);
    }
}