package com.example.monk_prj.utility;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.request.CreateCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@UtilityClass
@Slf4j
public class ConverterUtil {

    public <T> T getServiceRequest(ServiceRequest serviceRequest, Class<T> tClass){
        Optional<Object> bodyOptional = Optional.of(serviceRequest).map(ServiceRequest::getBody);
        if(bodyOptional.isEmpty()){
            log.error("body not found in serviceRequest = {}", serviceRequest);
            throw new RuntimeException();
        }
        Object body = bodyOptional.get();
        try{
            log.info("body : {}",body);
            return JsonMapper.convertObjectToClass(body, tClass);
        }catch (Exception ex){
            log.error("Error occurred while parsing request body, ",ex);
            throw new RuntimeException();
        }
    }
}
