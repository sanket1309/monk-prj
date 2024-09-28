package com.example.monk_prj.controller;

import com.example.monk_prj.constants.ApiEndpoints;
import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.ServiceResponse;
import com.example.monk_prj.service.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class CouponController {

    @Autowired
    CreateCouponService createCouponService;

    @Autowired
    GetCouponService getCouponService;

    @Autowired
    GetAllCouponService getAllCouponService;

    @Autowired
    UpdateCouponService updateCouponService;

    @Autowired
    DeleteCouponService deleteCouponService;

    @Autowired
    GetApplicableCouponService getApplicableCouponService;

    @Autowired
    ApplyCouponService applyCouponService;


    @RequestMapping(value = ApiEndpoints.CREATE_COUPON, method = RequestMethod.POST)
    ServiceResponse createCoupon(@RequestBody ServiceRequest serviceRequest, HttpServletResponse httpServletResponse){
        log.info("Request received for {}, request = {}", ApiEndpoints.CREATE_COUPON, serviceRequest);
        ServiceResponse serviceResponse = createCouponService.execute(serviceRequest);
        log.info("Response for {}, response = {}", ApiEndpoints.CREATE_COUPON, serviceResponse);
        return serviceResponse;
    }

    @RequestMapping(value = ApiEndpoints.GET_ALL_COUPONS, method = RequestMethod.GET)
    ServiceResponse getAllCoupons(HttpServletResponse httpServletResponse){
        log.info("Request received for {}", ApiEndpoints.GET_ALL_COUPONS);
        ServiceResponse serviceResponse = getAllCouponService.execute(new ServiceRequest());
        log.info("Response for {}, response = {}", ApiEndpoints.GET_ALL_COUPONS, serviceResponse);
        return serviceResponse;
    }

    @RequestMapping(value = ApiEndpoints.GET_COUPON_BY_ID, method = RequestMethod.GET)
    ServiceResponse getCoupon(@RequestParam Map<String, String> requestParams, HttpServletResponse httpServletResponse){
        log.info("Request received for {}, params = {}", ApiEndpoints.GET_COUPON_BY_ID, requestParams);
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setRequestParams(requestParams);
        ServiceResponse serviceResponse = getCouponService.execute(serviceRequest);
        log.info("Response for {}, response = {}", ApiEndpoints.GET_COUPON_BY_ID, serviceResponse);
        return serviceResponse;
    }

    @RequestMapping(value = ApiEndpoints.UPDATE_COUPON, method = RequestMethod.PUT)
    ServiceResponse updateCoupon(@RequestParam Map<String, String> requestParams,@RequestBody ServiceRequest serviceRequest, HttpServletResponse httpServletResponse){
        log.info("Request received for {}, request = {}", ApiEndpoints.UPDATE_COUPON, serviceRequest);
        serviceRequest.setRequestParams(requestParams);
        ServiceResponse serviceResponse = updateCouponService.execute(serviceRequest);
        log.info("Response for {}, response = {}", ApiEndpoints.UPDATE_COUPON, serviceResponse);
        return serviceResponse;
    }

    @RequestMapping(value = ApiEndpoints.DELETE_COUPON_BY_ID, method = RequestMethod.DELETE)
    ServiceResponse deleteCoupon(@RequestParam Map<String, String> requestParams, HttpServletResponse httpServletResponse){
        log.info("Request received for {}", ApiEndpoints.DELETE_COUPON_BY_ID);
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setRequestParams(requestParams);
        ServiceResponse serviceResponse = deleteCouponService.execute(serviceRequest);
        log.info("Response for {}, response = {}", ApiEndpoints.DELETE_COUPON_BY_ID, serviceResponse);
        return serviceResponse;
    }

    @RequestMapping(value = ApiEndpoints.GET_APPLICABLE_COUPONS, method = RequestMethod.POST)
    ServiceResponse getApplicableCoupons(@RequestBody ServiceRequest serviceRequest, HttpServletResponse httpServletResponse){
        log.info("Request received for {}, request = {}", ApiEndpoints.GET_APPLICABLE_COUPONS, serviceRequest);
        ServiceResponse serviceResponse = getApplicableCouponService.execute(serviceRequest);
        log.info("Response for {}, response = {}", ApiEndpoints.GET_APPLICABLE_COUPONS, serviceResponse);
        return serviceResponse;
    }

    @RequestMapping(value = ApiEndpoints.APPLY_COUPON, method = RequestMethod.POST)
    ServiceResponse applyCoupons(@RequestParam Map<String, String> requestParams,@RequestBody ServiceRequest serviceRequest, HttpServletResponse httpServletResponse){
        log.info("Request received for {}, params = {}, request = {}", ApiEndpoints.APPLY_COUPON, requestParams, serviceRequest);
        serviceRequest.setRequestParams(requestParams);
        ServiceResponse serviceResponse = applyCouponService.execute(serviceRequest);
        log.info("Response for {}, response = {}", ApiEndpoints.APPLY_COUPON, serviceResponse);
        return serviceResponse;
    }
}

