package com.example.monk_prj.model.coupon;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.enums.ErrorTypes;
import com.example.monk_prj.enums.ProductCategory;
import com.example.monk_prj.exception.CouponException;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.Product;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import com.example.monk_prj.model.id.ProductId;
import com.example.monk_prj.repository.ProductRepository;
import com.example.monk_prj.repository.impl.ProductRepositoryImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class ProductIdWiseCouponDetails extends ProductWiseCouponDetails {
    private List<ProductId> productIds;

    public static void validate(ProductIdWiseCouponDetails productIdWiseCouponDetails){
        ProductWiseCouponDetails.validate(productIdWiseCouponDetails);
        if(CollectionUtils.isEmpty(productIdWiseCouponDetails.getProductIds())){
            log.error("Empty product ids");
            throw new CouponException(ErrorTypes.INVALID_PRODUCT_ID);
        }
        for (ProductId productId : productIdWiseCouponDetails.getProductIds()){
            ProductId.validateId(productId);
        }
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.PRODUCT_WISE_BY_ID;
    }
}
