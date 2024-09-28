package com.example.monk_prj.strategy.impl;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.Product;
import com.example.monk_prj.model.coupon.BxGyIdWiseCouponDetails;
import com.example.monk_prj.model.coupon.CartWiseCouponDetails;
import com.example.monk_prj.model.coupon.CouponDetails;
import com.example.monk_prj.model.coupon.appliedcoupons.AppliedCouponCart;
import com.example.monk_prj.repository.ProductRepository;
import com.example.monk_prj.strategy.ApplyCouponStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BxGyIdWiseStrategy implements ApplyCouponStrategy {

    @Autowired
    ProductRepository productRepository;

    @Override
    public AppliedCouponCart applyCoupon(CouponDetails couponDetails, Cart cart) {
        BxGyIdWiseCouponDetails bxGyIdWiseCouponDetails = (BxGyIdWiseCouponDetails) couponDetails;
        AppliedCouponCart appliedCouponCart = new AppliedCouponCart(cart);
        AtomicInteger xTotal = new AtomicInteger(0);
        cart.getCartItems().forEach(
                productOrder -> {
                    String boughtId = productOrder.getProduct().getProductId().getId();
                    if(bxGyIdWiseCouponDetails.getProductBuyIds().contains(boughtId)){
                        xTotal.addAndGet(productOrder.getQuantity());
                    }
                }
        );
        if(xTotal.get() < bxGyIdWiseCouponDetails.getX()){
            return null;
        }
        int possibleRepetitions = Math.min(xTotal.get() / bxGyIdWiseCouponDetails.getX(), bxGyIdWiseCouponDetails.getRepetitionLimit());
        Product expensiveProduct = getExpensiveGetProduct(bxGyIdWiseCouponDetails);
        appliedCouponCart.addProduct(expensiveProduct, possibleRepetitions);
        appliedCouponCart.setDiscountAmount(expensiveProduct.getPrice() * possibleRepetitions);
        return appliedCouponCart;
    }

    public Product getExpensiveGetProduct(BxGyIdWiseCouponDetails bxGyIdWiseCouponDetails){
        int maxPrice = 0;
        Product expensiveProduct = null;
        for(String getId : bxGyIdWiseCouponDetails.getProductGetIds()){
            Product product = productRepository.get(getId);
            if(product != null && product.getPrice() > maxPrice){
                expensiveProduct = product;
            }
        }
        return expensiveProduct;
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.BXGY_BY_ID;
    }
}
