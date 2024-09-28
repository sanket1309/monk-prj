package com.example.monk_prj.strategy;


import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.coupon.CouponDetails;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;

public interface ApplyCouponStrategy {
    AppliedCouponCart applyCoupon(CouponDetails couponDetails, Cart cart);
    CouponType getCouponType();
}
