package com.xiangyu.account.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseResult {

    private String message = "";
    private Object data = "";

    public static ResponseResult buildSuccess() {
        ResponseResult successResult = new ResponseResult();
        successResult.setMessage(ResponseConstants.SUCCESS_MESSAGE);

        return successResult;
    }

    public static ResponseResult buildSuccess(Object data) {
        ResponseResult successResult = new ResponseResult();
        successResult.setMessage(ResponseConstants.SUCCESS_MESSAGE);
        successResult.setData(data);

        return successResult;
    }

    public static ResponseResult buildError() {
        ResponseResult errorResult = new ResponseResult();
        errorResult.setMessage(ResponseConstants.INTERNAL_ERROR);

        return errorResult;
    }

    public static ResponseResult buildError(String message) {
        ResponseResult errorResult = new ResponseResult();
        errorResult.setMessage(message);

        return errorResult;
    }
}
