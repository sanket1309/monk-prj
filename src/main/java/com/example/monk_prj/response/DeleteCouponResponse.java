package com.example.monk_prj.response;

import com.example.monk_prj.model.coupon.Coupon;
import lombok.Data;

@Data
public class DeleteCouponResponse extends ServiceResponse{
    private String deletedCouponId;
}
