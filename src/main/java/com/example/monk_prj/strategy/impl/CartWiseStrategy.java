package com.example.monk_prj.strategy.impl;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.CartWiseCouponDetails;
import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.coupon.CouponDetails;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import com.example.monk_prj.strategy.ApplyCouponStrategy;
import org.springframework.stereotype.Component;

@Component
public class CartWiseStrategy implements ApplyCouponStrategy {

    @Override
    public AppliedCouponCart applyCoupon(CouponDetails couponDetails, Cart cart) {
        CartWiseCouponDetails cartWiseCouponDetails = (CartWiseCouponDetails) couponDetails;
        if(cart.getTotal() < cartWiseCouponDetails.getThreshold()) return null;
        AppliedCouponCart appliedCouponCart = new AppliedCouponCart(cart);
        int discount = (int) (cart.getTotal() * cartWiseCouponDetails.getDiscount() * 1.0 / 100.0);
        appliedCouponCart.setDiscountAmount(discount);
        return appliedCouponCart;
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.CART_WISE;
    }
}
