package com.example.monk_prj.service;

import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.request.CreateCouponRequest;
import com.example.monk_prj.request.GetApplicableCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.CreateCouponResponse;
import com.example.monk_prj.response.GetApplicableCouponResponse;
import com.example.monk_prj.strategy.ApplyCouponStrategy;
import com.example.monk_prj.strategy.ApplyCouponStrategyFactory;
import com.example.monk_prj.utility.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class GetApplicableCouponService extends AbstractServiceStructure<GetApplicableCouponRequest, GetApplicableCouponResponse> {

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ApplyCouponStrategyFactory applyCouponStrategyFactory;

    @Override
    GetApplicableCouponRequest preProcess(ServiceRequest serviceRequest) {
        GetApplicableCouponRequest getApplicableCouponRequest = ConverterUtil.getServiceRequest(serviceRequest, GetApplicableCouponRequest.class);
        if(Objects.isNull(getApplicableCouponRequest)){
            log.error("getApplicableCouponRequest is null");
            throw new RuntimeException();
        }
        log.info("request : {}", getApplicableCouponRequest);
        Cart.validate(getApplicableCouponRequest.getCart());
        return getApplicableCouponRequest;
    }

    @Override
    GetApplicableCouponResponse process(GetApplicableCouponRequest getApplicableCouponRequest) {
        List<Coupon> applicableCoupons = couponRepository.getAll();
        applicableCoupons = applicableCoupons.stream().filter(coupon -> {
            ApplyCouponStrategy applyCouponStrategy = applyCouponStrategyFactory.findStrategy(coupon.getCouponType());
            if(applyCouponStrategy == null) return false;
            return applyCouponStrategy.applyCoupon(coupon.getCouponDetails(),getApplicableCouponRequest.getCart()) != null;
        }).toList();
        GetApplicableCouponResponse getApplicableCouponResponse = new GetApplicableCouponResponse();
        getApplicableCouponResponse.setApplicableCoupons(applicableCoupons);
        return getApplicableCouponResponse;
    }
}
