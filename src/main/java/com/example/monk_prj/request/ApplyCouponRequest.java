package com.example.monk_prj.request;

import com.example.monk_prj.model.Cart;
import lombok.Data;

@Data
public class ApplyCouponRequest {
    private String couponId;
    private Cart cart;
}
