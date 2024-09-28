package com.example.monk_prj.repository.impl;

import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.repository.CouponRepository;
import com.example.monk_prj.repository.jsonmodel.CouponData;
import com.example.monk_prj.utility.JsonMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Data
@Slf4j
public class CouponRepositoryImpl implements CouponRepository {
    @Autowired
    ResourceLoader resourceLoader;

    private Map<String, Coupon> coupons = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:json/coupons.json");

        // Convert Resource to Path and read content
        Path filePath = resource.getFile().toPath();
        String json = Files.readString(filePath);
        CouponData couponData = JsonMapper.mapJsonToObject(json, CouponData.class);
        coupons = couponData.getCoupons().stream().collect(Collectors.toMap(it -> it.getCouponId().getId(), it -> it));
    }
    @Override
    public void save(Coupon coupon){
        coupons.put(coupon.getCouponId().getId(), coupon);
    }
    @Override
    public Coupon get(String couponId){
        return coupons.get(couponId);
    }
    @Override
    public List<Coupon> getAll(){
        return new ArrayList<>(coupons.values());
    }

    @Override
    public void remove(String id) {
        coupons.remove(id);
    }
}
