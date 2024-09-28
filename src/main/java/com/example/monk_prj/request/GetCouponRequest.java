package com.example.monk_prj.request;

import com.example.monk_prj.model.coupon.Coupon;
import lombok.Data;

@Data
public class GetCouponRequest {
    private String couponId;
}
