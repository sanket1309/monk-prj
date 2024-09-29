package com.example.monk_prj.exception;

import com.example.monk_prj.enums.ErrorTypes;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CouponException extends RuntimeException{
    private ErrorTypes errorType;
    public CouponException(ErrorTypes errorType){
        this.errorType = errorType;
    }
    String description;
}
