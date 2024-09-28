package com.example.monk_prj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public enum CouponType {
    CART_WISE("CART_WISE"),
    PRODUCT_WISE_BY_CTG("PRODUCT_WISE_BY_CTG"),
    PRODUCT_WISE_BY_ID("PRODUCT_WISE_BY_ID"),
    BXGY_BY_CTG("BXGY_BY_CTG"),
    BXGY_BY_ID("BXGY_BY_ID");

    private final String name;

    @Override
    public String toString(){
        return name;
    }

    public static void validate(CouponType couponType){
        if(couponType == null){
            log.error("couponType is null");
            throw new RuntimeException();
        }
//        for(CouponType couponType_ : values()){
//            if(couponType == couponType_){
//                if(couponType_.getSubTypeClass() == null && couponSubType != null
//                || couponType_.getSubTypeClass() != null && !couponType_.getSubTypeClass().isInstance(couponSubType)){
//                    log.error("Invalid couponSubType = {}, for couponType = {}", couponSubType, couponType);
//                    throw new RuntimeException();
//                }
//            }
//        }
    }
}
