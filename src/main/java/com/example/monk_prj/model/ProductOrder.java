package com.example.monk_prj.model;

import com.example.monk_prj.enums.ErrorTypes;
import com.example.monk_prj.exception.CouponException;
import com.example.monk_prj.model.id.OrderId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {
    private Product product;
    private int quantity;

    public int getTotalPrice(){
        return product.getPrice() * quantity;
    }

    public static void validate(ProductOrder productOrder){
        if(Objects.isNull(productOrder)){
            log.error("productOrder is null");
            throw new CouponException(ErrorTypes.INVALID_PRODUCT_ORDER);
        }
        Product.validate(productOrder.getProduct());
        if(productOrder.getQuantity() < 1){
            log.error("quantity is invalid");
            throw new CouponException(ErrorTypes.INVALID_ORDER_QUANTITY);
        }
    }
}
