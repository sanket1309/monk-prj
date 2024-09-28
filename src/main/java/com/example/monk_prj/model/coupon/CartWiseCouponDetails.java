package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class CartWiseCouponDetails extends CouponDetails{
    private int threshold;
    public static void validate(CartWiseCouponDetails cartWiseCouponDetails){
        CouponDetails.validate(cartWiseCouponDetails);
        if(cartWiseCouponDetails.getThreshold() <= 0){
            log.error("Invalid threshold = {}", cartWiseCouponDetails.getThreshold());
            throw new RuntimeException();
        }
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.CART_WISE;
    }
}
