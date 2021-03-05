package oliver.springboot_template.global.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description MybatisPlusConfig
 * @Author Oliver
 * @Date 2020/8/14 14:08
 * @Version 1.0
 **/
@MapperScan("oliver.springboot_template.app.*.repository")
@EnableTransactionManagement
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
public class MybatisPlusConfig {

    /**
     * 分页插件
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @date: 2021-03-05 9:59
     * @author Oliver
    **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //这里选择自己数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
