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
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class BxGyIdWiseCouponDetails extends BxGyCouponDetails{
    private List<String> productBuyIds;
    private List<String> productGetIds;

    public static void validate(BxGyIdWiseCouponDetails bxGyIdWiseCouponDetails){
        BxGyCouponDetails.validate(bxGyIdWiseCouponDetails);
        if(CollectionUtils.isEmpty(bxGyIdWiseCouponDetails.getProductBuyIds())){
            log.error("buy ids are empty");
            throw new CouponException(ErrorTypes.INVALID_BUY_PRODUCT_IDS);
        }
        if(CollectionUtils.isEmpty(bxGyIdWiseCouponDetails.getProductGetIds())){
            log.error("get ids are empty");
            throw new CouponException(ErrorTypes.INVALID_GET_PRODUCT_IDS);
        }
        for(String id : bxGyIdWiseCouponDetails.getProductBuyIds()){
            ProductId.validateId(new ProductId(id));
        }
        for(String id : bxGyIdWiseCouponDetails.getProductGetIds()){
            ProductId.validateId(new ProductId(id));
        }
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.BXGY_BY_ID;
    }
}
