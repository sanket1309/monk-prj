package com.example.monk_prj.repository;

import com.example.monk_prj.model.coupon.Coupon;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CouponRepository {
    void save(Coupon coupon);
    Coupon get(String id);
    List<Coupon> getAll();
    void remove(String id);
}
