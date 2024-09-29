package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.enums.ErrorTypes;
import com.example.monk_prj.enums.ProductCategory;
import com.example.monk_prj.exception.CouponException;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class BxGyCategoryWiseCouponDetails extends BxGyCouponDetails{
    private List<ProductCategory> productBuyCategory;
    private List<ProductCategory> productGetCategory;
    public static void validate(BxGyCategoryWiseCouponDetails bxGyCategoryWiseCouponDetails){
        BxGyCouponDetails.validate(bxGyCategoryWiseCouponDetails);
        if(CollectionUtils.isEmpty(bxGyCategoryWiseCouponDetails.getProductBuyCategory())){
            log.error("Invalid product x category");
            throw new CouponException(ErrorTypes.INVALID_BUY_PRODUCT_CATEGORY);
        }
        if(CollectionUtils.isEmpty(bxGyCategoryWiseCouponDetails.getProductGetCategory())){
            log.error("Invalid product y category");
            throw new CouponException(ErrorTypes.INVALID_GET_PRODUCT_CATEGORY);
        }
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.BXGY_BY_CTG;
    }
}
