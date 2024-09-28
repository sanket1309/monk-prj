package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.model.id.CouponId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private CouponId couponId;
    private CouponType couponType;
    private CouponDetails couponDetails;

    public static void validate(Coupon coupon){
        if(Objects.isNull(coupon)){
            log.error("coupon is null");
            throw new RuntimeException();
        }

        if(Objects.isNull(coupon.getCouponId())){
            coupon.setCouponId(new CouponId());
        }
        CouponId.validateId(coupon.getCouponId());
        CouponType.validate(coupon.getCouponType());
        CouponDetails.validateAll(coupon.getCouponDetails());
    }
}
