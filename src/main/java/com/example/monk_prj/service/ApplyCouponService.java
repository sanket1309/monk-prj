package com.example.monk_prj.service;

import com.example.monk_prj.enums.ErrorTypes;
import com.example.monk_prj.exception.CouponException;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import com.example.monk_prj.model.id.CouponId;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.request.ApplyCouponRequest;
import com.example.monk_prj.request.GetApplicableCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.ApplyCouponResponse;
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
public class ApplyCouponService extends AbstractServiceStructure<ApplyCouponRequest, ApplyCouponResponse> {

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ApplyCouponStrategyFactory applyCouponStrategyFactory;

    @Override
    ApplyCouponRequest preProcess(ServiceRequest serviceRequest) {
        ApplyCouponRequest applyCouponRequest = ConverterUtil.getServiceRequest(serviceRequest, ApplyCouponRequest.class);
        if(Objects.isNull(applyCouponRequest)){
            log.error("applyCouponRequest is null");
            throw new CouponException(ErrorTypes.INVALID_REQUEST_BODY);
        }
        applyCouponRequest.setCouponId(serviceRequest.getRequestParams().getOrDefault("id", null));
        log.info("request : {}", applyCouponRequest);
        CouponId.validateId(new CouponId(applyCouponRequest.getCouponId()));
        Cart.validate(applyCouponRequest.getCart());
        return applyCouponRequest;
    }

    @Override
    ApplyCouponResponse process(ApplyCouponRequest applyCouponRequest) {
        Coupon coupon = couponRepository.get(applyCouponRequest.getCouponId());

        AppliedCouponCart appliedCouponCart = null;
        ApplyCouponStrategy applyCouponStrategy = applyCouponStrategyFactory.findStrategy(coupon.getCouponType());
        if(applyCouponStrategy != null){
            appliedCouponCart = applyCouponStrategy.applyCoupon(coupon.getCouponDetails(),applyCouponRequest.getCart());
        }
        ApplyCouponResponse applyCouponResponse = new ApplyCouponResponse();
        applyCouponResponse.setUpdatedCart(appliedCouponCart);
        return applyCouponResponse;
    }
}
