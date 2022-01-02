package com.project.activeedge.models.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SuccessResponse implements Serializable {
    private Integer responseCode = 99;
    private String message;
    private Object responseData;

    public SuccessResponse(String message, Object responseData) {
        this.message = message;
        this.responseData = responseData;
    }

    public SuccessResponse(Object responseData) {
        this.responseData = responseData;
    }
}
