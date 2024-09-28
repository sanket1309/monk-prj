package com.example.monk_prj.strategy.impl;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.enums.ProductCategory;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.Product;
import com.example.monk_prj.model.coupon.BxGyIdWiseCouponDetails;
import com.example.monk_prj.model.coupon.CouponDetails;
import com.example.monk_prj.model.coupon.ProductIdWiseCouponDetails;
import com.example.monk_prj.model.coupon.ProductWiseCouponDetails;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import com.example.monk_prj.repository.ProductRepository;
import com.example.monk_prj.strategy.ApplyCouponStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ProductIdWiseStrategy implements ApplyCouponStrategy {

    @Autowired
    ProductRepository productRepository;

    @Override
    public AppliedCouponCart applyCoupon(CouponDetails couponDetails, Cart cart) {
        ProductIdWiseCouponDetails productIdWiseCouponDetails = (ProductIdWiseCouponDetails) couponDetails;
        AppliedCouponCart appliedCouponCart = new AppliedCouponCart(cart);
        AtomicInteger discount = new AtomicInteger(0);
        cart.getCartItems().forEach(
                productOrder -> {
                    String boughtId = productOrder.getProduct().getProductId().getId();
                    if(productIdWiseCouponDetails.getProductIds().contains(boughtId)){
                        Product product = productRepository.get(boughtId);
                        if(product != null){
                            discount.addAndGet((int)((product.getPrice() * productIdWiseCouponDetails.getDiscount() * 1.0) / 100.0));
                        }
                    }
                }
        );
        appliedCouponCart.setDiscountAmount(discount.get());
        return appliedCouponCart;
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.PRODUCT_WISE_BY_ID;
    }
}
