package com.example.monk_prj.response;

import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import lombok.Data;

import java.util.List;

@Data
public class GetApplicableCouponResponse extends ServiceResponse{
    private List<Coupon> applicableCoupons;
}
