package oliver.springboot_template.global.handler;

import oliver.springboot_template.global.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Oliver
 */ // 只拦截我们的 Controller 所在包，避免其它类似 swagger 提供的 API 被切面拦截
    /**
     *@description:
     *@param:
     *@return:
     *@author:  Oliver
     *@date  2021/3/5
     *@修改人和其它信息
     */
@ControllerAdvice(basePackages = "oliver.springboot_template.app.sys.controller")
public class GlobalResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 如果已经是 R 类型，则直接返回
        if (body instanceof CommonResponse) {
            return body;
        }
        // 如果不是，则包装成 R 类型
        return CommonResponse.ok(body.toString());
    }

}
