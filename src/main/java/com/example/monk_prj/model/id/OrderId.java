package com.example.monk_prj.model.id;

import com.example.monk_prj.enums.IdPrefix;

public class OrderId extends Id{
    @Override
    IdPrefix getIdPrefix() {
        return IdPrefix.ORDER_ID;
    }
}
