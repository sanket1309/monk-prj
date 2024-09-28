package com.example.monk_prj.model.coupon.appliedcoupons;

import com.example.monk_prj.model.Cart;
import com.example.monk_prj.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppliedCouponCart extends Cart{
    private int discountAmount;
    private int finalTotal;
    public AppliedCouponCart(Cart cart){
        super(cart.getCartId(), new ArrayList<>(cart.getCartItems()), cart.getTotal());
    }
    public void setDiscountAmount(int discount){
        this.discountAmount = discount;
        finalTotal = getTotal() - discount;
    }
}
