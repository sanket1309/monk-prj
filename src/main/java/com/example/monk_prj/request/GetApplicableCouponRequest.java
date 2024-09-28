package com.example.monk_prj.request;

import com.example.monk_prj.model.Cart;
import com.example.monk_prj.model.coupon.Coupon;
import lombok.Data;

@Data
public class GetApplicableCouponRequest {
    private Cart cart;
}
