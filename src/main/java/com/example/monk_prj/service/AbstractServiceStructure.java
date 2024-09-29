package com.example.monk_prj.service;

import com.example.monk_prj.exception.CouponException;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.ServiceResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public abstract class AbstractServiceStructure<SERVICE_REQUEST, SERVICE_RESPONSE extends ServiceResponse> {

    public ServiceResponse execute(ServiceRequest serviceRequest, HttpServletResponse httpServletResponse){
        SERVICE_REQUEST specificServiceRequest;
        ServiceResponse serviceResponse = null;
        try{
            specificServiceRequest = preProcess(serviceRequest);
        }
        catch (CouponException couponException){
            log.error("CouponException occurred while pre-processing request, ", couponException);
            httpServletResponse.setStatus(couponException.getErrorType().getHttpStatus().value());
            serviceResponse = new ServiceResponse();
            serviceResponse.setStatus("FAILED");
            serviceResponse.setDescription(couponException.getErrorType().getName());
            return serviceResponse;
        }
        catch (Exception preProcessException){
            log.error("Exception occurred while pre-processing request, ", preProcessException);
            throw preProcessException;
        }

        try{
            serviceResponse = process(specificServiceRequest);
            serviceResponse.setStatus("SUCCESS");
        }
        catch (CouponException couponException){
            log.error("CouponException occurred while processing request, ", couponException);
            httpServletResponse.setStatus(couponException.getErrorType().getHttpStatus().value());
            return serviceResponse;
        }
        catch (Exception processException){
            log.error("Exception occurred while processing request, ", processException);
            throw processException;
        }
        return serviceResponse;
    }
    //MEANT FOR REQUEST VALIDATION
    abstract SERVICE_REQUEST preProcess(ServiceRequest serviceRequest);

    //FOR MAIN PROCESSING
    abstract SERVICE_RESPONSE process(SERVICE_REQUEST serviceRequest);
}
