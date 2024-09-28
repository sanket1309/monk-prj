package com.example.monk_prj.service;

import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.id.CouponId;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.request.GetCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.GetAllCouponsResponse;
import com.example.monk_prj.response.GetCouponResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class GetAllCouponService extends AbstractServiceStructure<ServiceRequest, GetAllCouponsResponse> {

    @Autowired
    CouponRepository couponRepository;

    @Override
    ServiceRequest preProcess(ServiceRequest serviceRequest) {
        return serviceRequest;
    }

    @Override
    GetAllCouponsResponse process(ServiceRequest getCouponRequest) {
        List<Coupon> coupons = couponRepository.getAll();
        GetAllCouponsResponse getAllCouponsResponse = new GetAllCouponsResponse();
        getAllCouponsResponse.setCoupons(coupons);
        return getAllCouponsResponse;
    }
}
