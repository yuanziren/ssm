package com.shsxt.exception;

import com.shsxt.base.ParamException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@Component
public class GlobalException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, // 请求
                                         HttpServletResponse response, // 响应
                                         Object target, // 目标对象
                                         Exception ex) { // 异常
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");// 默认错误视图
        mv.addObject("ex", ex.getMessage());// 默认错误信息

        /***
         * 对不同的错误进行不同的处理
         * */
        if(ex instanceof ParamException){
            System.out.println("ParamException...");
            ParamException e = (ParamException) ex;
            mv.setViewName("pa_error");// 单独指定返回视图
            mv.addObject("ex", e.getMessage());// 单独执行错误返回信息
        }
        return mv;
    }
}
