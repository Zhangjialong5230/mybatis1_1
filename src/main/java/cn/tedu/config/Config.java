package cn.tedu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
/**
 * MapperScan 注解是MyBetis提供的 器作用是Mapper接口扫描功能
 * 就是将 定义 SQL  和接口方法UserDao 创建（自动实现接口创建对象）
 * 为对象创建后的对象 ，自动保存到Spring 容器中， Bean ID 是接口名首字母小写
 * 也就是UserDao创建对象以后其ID 为userDao
 * 可以利用getBean 根据类型 UserDao。class 或者ID userDao 获取接口子类的实例
 * MapperScan的参数就是 这些数据访问接口所在的包cn.tedu.dao
 * 这个cn.tedu.dao 包的子包也会被扫描到
 *
 *
 */
@MapperScan("cn.tedu.dao")
public class Config {

    /**
     * 配置MyBatis的SqlSession工厂 目的是将SQL连接池注入给MyBatis
     * 使MyBatis能够找到数据库
     */

    @Bean
    public SqlSessionFactory sqlSessionFactory
    (DataSource dataSource)throws  Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return  bean.getObject();
    }


    @Bean
    public DataSource dataSource(
            @Value("${db.driver}") String driver,
            @Value("${db.url}") String url,
            @Value("${db.username}") String username,
            @Value("${db.password}") String password){

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

}
