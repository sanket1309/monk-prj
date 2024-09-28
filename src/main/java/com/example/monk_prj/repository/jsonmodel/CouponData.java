package com.example.monk_prj.repository.jsonmodel;

import com.example.monk_prj.model.coupon.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponData {
    List<Coupon> coupons;
}
