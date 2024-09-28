package com.example.monk_prj.request;

import com.example.monk_prj.model.coupon.Coupon;
import lombok.Data;

@Data
public class CreateCouponRequest {
    private Coupon coupon;
}
