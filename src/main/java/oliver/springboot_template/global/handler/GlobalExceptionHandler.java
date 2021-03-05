package oliver.springboot_template.global.handler;

import oliver.springboot_template.global.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author Oliver
 */
@ControllerAdvice(basePackages = "oliver.springboot_template.app.sys.controller")
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理 ServiceException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResponse serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        // 包装结果
        return CommonResponse.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     * <p>
     * SpringMVC 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResponse missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        logger.debug("[missingServletRequestParameterExceptionHandler]", ex);
        // 包装结果
        return CommonResponse.error(4001,
                ex.getMessage());
    }

    /**
     * 拦截 validation 报出的ConstraintViolationException
     * @return oliver.springboot_template.global.CommonResponse
     * @date: 2021-03-05 11:38
     * @author Oliver
     **/

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResponse constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        logger.debug("[constraintViolationExceptionHandler]", ex);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(constraintViolation.getMessage());
        }
        // 包装结果
        return CommonResponse.error(4002,
                "Invalid Request Parameter :" + detailMessage.toString());
    }

    /**
     * 拦截 springboot 报出的BindException
     * @return oliver.springboot_template.global.CommonResponse
     * @date: 2021-03-05 11:38
     * @author Oliver
     **/
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResponse bindExceptionHandler(HttpServletRequest req, BindException ex) {
        logger.debug("[bindExceptionHandler]", ex);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : ex.getAllErrors()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(objectError.getDefaultMessage());
        }
        // 包装结果
        return CommonResponse.error(4002,
                "Invalid Request Parameter :" + detailMessage.toString());
    }

    /**
     * 拦截 @validated 报出的MethodArgumentNotValidException
     * @return oliver.springboot_template.global.CommonResponse
     * @date: 2021-03-05 11:38
     * @author Oliver
    **/
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResponse methodArgumentNotValidExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException ex) {
        logger.debug("[methodArgumentNotValidExceptionHandler]", ex);
        // 包装 结果
        List<FieldError> allErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder("Invalid Request Parameter : ");
        int time = 1;
        for (FieldError error : allErrors) {
            stringBuilder.append(time).append(". key[").append(error.getField()).append("] : [").append(error.getDefaultMessage()).append("];  ");
            time++;
        }
        return CommonResponse.error(4003,
                stringBuilder.toString());
    }


    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResponse exceptionHandler(HttpServletRequest req, Exception e) {
        // 记录异常日志
        logger.error("[exceptionHandler]", e);
        // 返回
        return CommonResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + " message : 【" + e.getMessage() + "】");
    }

}
