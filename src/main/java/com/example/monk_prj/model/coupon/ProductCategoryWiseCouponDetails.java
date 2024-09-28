package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.enums.ProductCategory;
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
public class ProductCategoryWiseCouponDetails extends ProductWiseCouponDetails {
    List<ProductCategory> productCategories;
    public static void validate(ProductCategoryWiseCouponDetails productWiseCouponDetails){
        ProductWiseCouponDetails.validate(productWiseCouponDetails);
        if(CollectionUtils.isEmpty(productWiseCouponDetails.getProductCategories())){
            log.error("Empty product categories");
            throw new RuntimeException();
        }
        for (ProductCategory productCategory : productWiseCouponDetails.getProductCategories()){
            if(productCategory == null){
                log.error("Invalid product category");
                throw new RuntimeException();
            }
        }
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.PRODUCT_WISE_BY_CTG;
    }
}
