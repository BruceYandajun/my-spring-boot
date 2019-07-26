package com.github.bruce.model.rpc;

import lombok.Data;

@Data
public class ApiResult<T> {

    private T data;

    public static <T> ApiResult<T> getApiResult(T data){
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        return result;
    }
}
