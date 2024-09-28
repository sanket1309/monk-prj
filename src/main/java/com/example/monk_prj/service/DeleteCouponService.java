package com.example.monk_prj.service;

import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.id.CouponId;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.request.DeleteCouponRequest;
import com.example.monk_prj.request.GetCouponRequest;
import com.example.monk_prj.request.ServiceRequest;
import com.example.monk_prj.response.DeleteCouponResponse;
import com.example.monk_prj.response.GetCouponResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class DeleteCouponService extends AbstractServiceStructure<DeleteCouponRequest, DeleteCouponResponse> {

    @Autowired
    CouponRepository couponRepository;

    @Override
    DeleteCouponRequest preProcess(ServiceRequest serviceRequest) {
        DeleteCouponRequest deleteCouponRequest = new DeleteCouponRequest();
        if(!CollectionUtils.isEmpty(serviceRequest.getRequestParams())){
            deleteCouponRequest.setCouponId(serviceRequest.getRequestParams().getOrDefault("id",null));
        }
        log.info("request : {}", deleteCouponRequest);
        CouponId couponId = new CouponId();
        couponId.setId(deleteCouponRequest.getCouponId());
        CouponId.validateId(couponId);
        return deleteCouponRequest;
    }

    @Override
    DeleteCouponResponse process(DeleteCouponRequest deleteCouponRequest) {
        couponRepository.remove(deleteCouponRequest.getCouponId());
        DeleteCouponResponse deleteCouponResponse = new DeleteCouponResponse();
        deleteCouponResponse.setDeletedCouponId(deleteCouponRequest.getCouponId());
        return deleteCouponResponse;
    }
}
