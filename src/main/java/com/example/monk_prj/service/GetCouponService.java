package com.example.monk_prj.service;

import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.id.CouponId;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.request.CreateCouponRequest;
import com.example.monk_prj.request.GetCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.CreateCouponResponse;
import com.example.monk_prj.response.GetCouponResponse;
import com.example.monk_prj.utility.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

@Service
@Slf4j
public class GetCouponService extends AbstractServiceStructure<GetCouponRequest, GetCouponResponse> {

    @Autowired
    CouponRepository couponRepository;

    @Override
    GetCouponRequest preProcess(ServiceRequest serviceRequest) {
        GetCouponRequest getCouponRequest = new GetCouponRequest();
        if(!CollectionUtils.isEmpty(serviceRequest.getRequestParams())){
            getCouponRequest.setCouponId(serviceRequest.getRequestParams().getOrDefault("id",null));
        }
        log.info("request : {}", getCouponRequest);
        CouponId couponId = new CouponId();
        couponId.setId(getCouponRequest.getCouponId());
        CouponId.validateId(couponId);
        return getCouponRequest;
    }

    @Override
    GetCouponResponse process(GetCouponRequest getCouponRequest) {
        Coupon coupon = couponRepository.get(getCouponRequest.getCouponId());
        GetCouponResponse getCouponResponse = new GetCouponResponse();
        getCouponResponse.setCoupon(coupon);
        return getCouponResponse;
    }
}
