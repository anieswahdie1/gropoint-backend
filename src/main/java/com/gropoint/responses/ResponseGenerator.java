package com.gropoint.responses;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator<T> {
    public <T> CommonResponse<T> success(T datas, String message){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(HttpStatus.OK.value());
        commonResponse.setMessage(message);
        commonResponse.setDatas(datas);

        return commonResponse;
    }

    public <T> CommonResponse<T> failed(String message){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        commonResponse.setMessage(message);

        return commonResponse;
    }
}
