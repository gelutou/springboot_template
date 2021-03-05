package oliver.springboot_template.global.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName DataObjectHandler
 * @Description mysql-plus的自动填充
 * @Author Oliver
 * @Date 2020/8/14 10:55
 * @Version 1.0
 **/
@Component
public class MybatisPlusAutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        long userId;
        try {
            //userId = ShiroUtils.getUserId() == null ? 1L : ShiroUtils.getUserId();
            userId = 1L;
        } catch (Exception e) {
            userId = 1L;
        }
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "revision", Integer.class, 1);
        this.strictInsertFill(metaObject, "updatedBy", Long.class, userId);
        this.strictInsertFill(metaObject, "createdBy", Long.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        long userId;
        try {
            //userId = ShiroUtils.getUserId() == null ? 1L : ShiroUtils.getUserId();
            userId = 1L;
        } catch (Exception e) {
            userId = 1L;
        }
        this.strictUpdateFill(metaObject, "updatedTime", String.class, DateUtil.now());
        this.strictUpdateFill(metaObject, "updatedBy", Long.class, userId);
    }
}
