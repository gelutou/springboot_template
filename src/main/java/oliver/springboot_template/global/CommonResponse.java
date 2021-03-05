package oliver.springboot_template.global;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 返回信息模板
 * @author: Oliver
 * @date 2020/9/15
 */
public class CommonResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public CommonResponse() {
        put("code", 200);
        put("msg", "success");
    }

    public static CommonResponse error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static CommonResponse error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static CommonResponse error(int code, String msg) {
        CommonResponse r = new CommonResponse();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static CommonResponse ok(String msg) {
        CommonResponse r = new CommonResponse();
        r.put("msg", msg);
        return r;
    }

    public static CommonResponse data(Object o) {
        CommonResponse r = new CommonResponse();
        r.put("data", o);
        return r;
    }

    public static CommonResponse ok(Map<String, Object> map) {
        CommonResponse r = new CommonResponse();
        r.putAll(map);
        return r;
    }

    public static CommonResponse ok() {
        return new CommonResponse();
    }

    public static CommonResponse page(Page<?> page) {
        CommonResponse r = new CommonResponse();
        r.put("code", 200);
        r.put("msg", "success");
        r.put("data", page.getRecords());
        r.put("count", page.getTotal());
        return r;
    }

    @Override
    public CommonResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
