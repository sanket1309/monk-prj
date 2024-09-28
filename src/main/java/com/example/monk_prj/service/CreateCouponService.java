package com.example.monk_prj.service;

import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.coupon.ProductIdWiseCouponDetails;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.request.CreateCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.CreateCouponResponse;
import com.example.monk_prj.utility.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class CreateCouponService extends AbstractServiceStructure<CreateCouponRequest, CreateCouponResponse> {

    @Autowired
    CouponRepository couponRepository;

    @Override
    CreateCouponRequest preProcess(ServiceRequest serviceRequest) {
        CreateCouponRequest createCouponRequest = ConverterUtil.getServiceRequest(serviceRequest, CreateCouponRequest.class);
        if(Objects.isNull(createCouponRequest)){
            log.error("CreateCouponRequest is null");
            throw new RuntimeException();
        }
        log.info("request : {}", createCouponRequest);
        Coupon.validate(createCouponRequest.getCoupon());
        return createCouponRequest;
    }

    @Override
    CreateCouponResponse process(CreateCouponRequest createCouponRequest) {
        couponRepository.save(createCouponRequest.getCoupon());
        CreateCouponResponse createCouponResponse = new CreateCouponResponse();
        createCouponResponse.setCouponId(createCouponRequest.getCoupon().getCouponId().getId());
        return createCouponResponse;
    }
}
