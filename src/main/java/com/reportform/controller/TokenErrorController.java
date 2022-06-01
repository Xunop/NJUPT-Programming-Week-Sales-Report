package com.reportform.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

/**
 * @Author xun
 * @create 2022/6/1 15:37
 */
@Controller
public class TokenErrorController implements ErrorController {
    private static final String path_default = "/error";

    @RequestMapping(value = path_default,  produces = {MediaType.APPLICATION_JSON_VALUE})
    public String error(HttpServletRequest request) {
        return "error";
        //从request中取出错误信息
/*        Result result = (Result) request.getAttribute("error");
        return result;*/
    }
}
