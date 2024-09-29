package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.enums.ErrorTypes;
import com.example.monk_prj.exception.CouponException;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "couponType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartWiseCouponDetails.class, name = "CART_WISE"),
        @JsonSubTypes.Type(value = BxGyCategoryWiseCouponDetails.class, name = "BXGY_BY_CTG"),
        @JsonSubTypes.Type(value = BxGyIdWiseCouponDetails.class, name = "BXGY_BY_ID"),
        @JsonSubTypes.Type(value = ProductIdWiseCouponDetails.class, name = "PRODUCT_WISE_BY_ID"),
        @JsonSubTypes.Type(value = ProductCategoryWiseCouponDetails.class, name = "PRODUCT_WISE_BY_CTG")
})
public abstract class CouponDetails {
    private int discount;
    @JsonIgnore
    public abstract CouponType getCouponType();
    public static void validate(CouponDetails couponDetails){
        if(couponDetails == null){
            log.error("couponDetails is null");
            throw new CouponException(ErrorTypes.INVALID_COUPON_DETAILS);
        }
        if(couponDetails.getDiscount() <= 0 || couponDetails.getDiscount() >= 100){
            log.error("Invalid discount = {}", couponDetails.getDiscount());
            throw new CouponException(ErrorTypes.INVALID_DISCOUNT_PERCENT);
        }
    }

    public static void validateAll(CouponDetails couponDetails){
        if(couponDetails == null){
            log.error("couponDetails is null");
            throw new CouponException(ErrorTypes.INVALID_COUPON_DETAILS);
        }
        if(couponDetails instanceof CartWiseCouponDetails){
            CartWiseCouponDetails.validate((CartWiseCouponDetails)couponDetails);
        }else if(couponDetails instanceof BxGyCouponDetails){
            BxGyCouponDetails.validateAll((BxGyCouponDetails)couponDetails);
        }else if(couponDetails instanceof ProductWiseCouponDetails){
            ProductWiseCouponDetails.validateAll((ProductWiseCouponDetails)couponDetails);
        }else{
            log.error("couponDetails is of invalid type");
            throw new CouponException(ErrorTypes.INVALID_COUPON_DETAILS);
        }
    }
}
