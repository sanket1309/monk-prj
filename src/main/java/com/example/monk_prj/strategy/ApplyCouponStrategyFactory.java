package com.example.monk_prj.strategy;

import com.example.monk_prj.enums.CouponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Set;

@Component
public class ApplyCouponStrategyFactory {
    private EnumMap<CouponType, ApplyCouponStrategy> strategies;

    @Autowired
    public ApplyCouponStrategyFactory(Set<ApplyCouponStrategy> strategySet){
        createStrategy(strategySet);
    }

    public ApplyCouponStrategy findStrategy(CouponType couponType){
        return strategies.get(couponType);
    }

    private void createStrategy(Set<ApplyCouponStrategy> strategySet){
        strategies = new EnumMap<>(CouponType.class);
        strategySet.forEach(strategy -> strategies.put(strategy.getCouponType(), strategy));
    }

}
