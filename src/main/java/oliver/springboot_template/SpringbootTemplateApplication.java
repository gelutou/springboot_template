package oliver.springboot_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Oliver
 */
@SpringBootApplication
//Spring AOP 将当前代理对象设置到 AopContext 中
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringbootTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTemplateApplication.class, args);
    }

}
