package com.example.monk_prj.response;

import com.example.monk_prj.model.coupon.Coupon;
import lombok.Data;

import java.util.List;

@Data
public class GetAllCouponsResponse extends ServiceResponse{
    private List<Coupon> coupons;
}
