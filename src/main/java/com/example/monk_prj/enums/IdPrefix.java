package com.example.monk_prj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum IdPrefix {
    PRODUCT_ID("PRD"),
    CART_ID("CRT"),
    COUPON_ID("CPN"),
    ORDER_ID("ORD");

    private String prefix;
}
