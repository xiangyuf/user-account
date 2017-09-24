package com.xiangyu.account.utility;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {
    private Gson gson = new Gson();

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handleUnexpectedException(HttpServletRequest request, Exception ex) {
        log.error("Error Mesage：{}, Request URI：{}, request params: {}", ex.getMessage(), request.getRequestURI(),
                gson.toJson(request.getParameterMap()));

        return ResponseResult.buildError(ResponseConstants.INTERNAL_ERROR);
    }
}
