package com.example.monk_prj.response;

import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import lombok.Data;

@Data
public class ApplyCouponResponse extends ServiceResponse{
    private AppliedCouponCart updatedCart;
}
