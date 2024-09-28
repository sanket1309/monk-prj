package com.example.monk_prj.service;

import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public abstract class AbstractServiceStructure<SERVICE_REQUEST, SERVICE_RESPONSE extends ServiceResponse> {

    public ServiceResponse execute(ServiceRequest serviceRequest){
        SERVICE_REQUEST specificServiceRequest;
        try{
            specificServiceRequest = preProcess(serviceRequest);
        }catch (Exception preProcessException){
            log.error("Exception occurred while pre-processing request, ", preProcessException);
            throw preProcessException;
        }

        try{
            return process(specificServiceRequest);
        }catch (Exception processException){
            log.error("Exception occurred while processing request, ", processException);
            throw processException;
        }
    }
    //MEANT FOR REQUEST VALIDATION
    abstract SERVICE_REQUEST preProcess(ServiceRequest serviceRequest);

    //FOR MAIN PROCESSING
    abstract SERVICE_RESPONSE process(SERVICE_REQUEST serviceRequest);
}
