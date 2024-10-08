package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.ErrorTypes;
import com.example.monk_prj.exception.CouponException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public abstract class BxGyCouponDetails extends CouponDetails{
    int x;
    int y;
    int repetitionLimit;
    public static void validate(BxGyCouponDetails bxGyCouponDetails){
        CouponDetails.validate(bxGyCouponDetails);
        if(bxGyCouponDetails.getX() <= 0){
            log.error("Invalid x = {}", bxGyCouponDetails.getX());
            throw new CouponException(ErrorTypes.INVALID_X_AMOUNT);
        }
        if(bxGyCouponDetails.getY() <= 0){
            log.error("Invalid y = {}", bxGyCouponDetails.getY());
            throw new CouponException(ErrorTypes.INVALID_Y_AMOUNT);
        }
        if(bxGyCouponDetails.getRepetitionLimit() <= 0){
            log.error("Repitition limit = {}", bxGyCouponDetails.getRepetitionLimit());
            throw new CouponException(ErrorTypes.INVALID_REPETITION_LIMIT);
        }
    }
    public static void validateAll(BxGyCouponDetails bxGyCouponDetails){
        CouponDetails.validate(bxGyCouponDetails);
        if(bxGyCouponDetails instanceof BxGyCategoryWiseCouponDetails){
            BxGyCategoryWiseCouponDetails.validate((BxGyCategoryWiseCouponDetails)bxGyCouponDetails);
        }else if(bxGyCouponDetails instanceof BxGyIdWiseCouponDetails){
            BxGyIdWiseCouponDetails.validate((BxGyIdWiseCouponDetails)bxGyCouponDetails);
        }else{
            log.error("bxGyCouponDetails is invalid");
            throw new CouponException(ErrorTypes.INVALID_COUPON_DETAILS);
        }
    }
}
