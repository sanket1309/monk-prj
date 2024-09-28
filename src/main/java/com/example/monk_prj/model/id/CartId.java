package com.example.monk_prj.model.id;

import com.example.monk_prj.enums.IdPrefix;

public class CartId extends Id{
    @Override
    IdPrefix getIdPrefix() {
        return IdPrefix.CART_ID;
    }
}
