package com.example.monk_prj.model.id;

import com.example.monk_prj.enums.IdPrefix;

public class ProductId extends Id{
    public ProductId(){
        super();
    }
    public ProductId(String id){
        this.id = id;
    }
    @Override
    IdPrefix getIdPrefix() {
        return IdPrefix.PRODUCT_ID;
    }
}
