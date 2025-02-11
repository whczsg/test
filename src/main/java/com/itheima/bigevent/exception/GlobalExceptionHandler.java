package com.itheima.bigevent.exception;

import com.itheima.bigevent.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
异常处理类
 */
@RestControllerAdvice//全局范围内处理控制层的异常，并以json，xml返回客户端
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//捕捉指定异常
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }
}
