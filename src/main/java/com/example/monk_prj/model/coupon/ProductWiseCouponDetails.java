package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.ProductCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public abstract class ProductWiseCouponDetails extends CouponDetails{
    public static void validate(ProductWiseCouponDetails productWiseCouponDetails){
        CouponDetails.validate(productWiseCouponDetails);
    }
    public static void validateAll(ProductWiseCouponDetails productWiseCouponDetails){
        CouponDetails.validate(productWiseCouponDetails);
        if(productWiseCouponDetails instanceof ProductCategoryWiseCouponDetails){
            ProductCategoryWiseCouponDetails.validate(productWiseCouponDetails);
        }else if(productWiseCouponDetails instanceof ProductIdWiseCouponDetails){
            ProductIdWiseCouponDetails.validate(productWiseCouponDetails);
        }else{
            log.error("productWiseCouponDetails is invalid");
            throw new RuntimeException();
        }
    }
}
