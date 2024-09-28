package com.example.monk_prj.service;

import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.request.CreateCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.request.UpdateCouponRequest;
import com.example.monk_prj.response.CreateCouponResponse;
import com.example.monk_prj.response.UpdateCouponResponse;
import com.example.monk_prj.utility.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UpdateCouponService extends AbstractServiceStructure<UpdateCouponRequest, UpdateCouponResponse> {

    @Autowired
    CouponRepository couponRepository;

    @Override
    UpdateCouponRequest preProcess(ServiceRequest serviceRequest) {
        UpdateCouponRequest updateCouponRequest = ConverterUtil.getServiceRequest(serviceRequest, UpdateCouponRequest.class);
        if(Objects.isNull(updateCouponRequest)){
            log.error("CreateCouponRequest is null");
            throw new RuntimeException();
        }
        log.info("request : {}", updateCouponRequest);
        Coupon.validate(updateCouponRequest.getCoupon());
        return updateCouponRequest;
    }

    @Override
    UpdateCouponResponse process(UpdateCouponRequest updateCouponRequest) {
        couponRepository.save(updateCouponRequest.getCoupon());
        UpdateCouponResponse updateCouponResponse = new UpdateCouponResponse();
        updateCouponResponse.setUpdatedCoupon(updateCouponRequest.getCoupon());
        return updateCouponResponse;
    }
}
