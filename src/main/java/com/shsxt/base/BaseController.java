package com.shsxt.base;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    @ExceptionHandler
    public String exc(HttpServletRequest request, // 请求
                      HttpServletResponse response, // 响应
                      Exception ex){

        request.setAttribute("ex", ex.getMessage());

        /***
         * 对不同的错误进行不同的处理
         * */
        if(ex instanceof ParamException){
            System.out.println("ParamException...ExceptionHandler");
            ParamException e = (ParamException) ex;
            request.setAttribute("ex", e.getMessage());// 单独执行错误返回信息
            return "pa_error";//单独执行错误返回信息
        }
        return "error";
    }
}
