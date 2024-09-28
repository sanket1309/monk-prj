package com.example.monk_prj.model.id;

import com.example.monk_prj.enums.IdPrefix;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CouponId extends Id{
    public CouponId(){
        super();
    }
    public CouponId(String id){
        this.id = id;
    }
    @Override
    IdPrefix getIdPrefix() {
        return IdPrefix.COUPON_ID;
    }
}
