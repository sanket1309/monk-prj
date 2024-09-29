package com.example.monk_prj.model;

import com.example.monk_prj.enums.ErrorTypes;
import com.example.monk_prj.enums.ProductCategory;
import com.example.monk_prj.exception.CouponException;
import com.example.monk_prj.model.id.CouponId;
import com.example.monk_prj.model.id.Id;
import com.example.monk_prj.model.id.ProductId;
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
public class Product {
    private ProductId productId;
    private ProductCategory productCategory;
    private String productName;
    private int price;

    public static boolean equals(Product p1, Product p2){
        if(p1 == null || p2 == null) return false;
        return ProductId.equals(p1.getProductId(), p2.getProductId());
    }

    public static void validate(Product product){
        if(Objects.isNull(product)){
            log.error("product is null");
            throw new CouponException(ErrorTypes.INVALID_PRODUCT,"product is null");
        }
        if(Objects.isNull(product.getProductId())){
            product.setProductId(new ProductId());
        }
        ProductId.validateId(product.getProductId());
        if(StringUtils.isBlank(product.getProductName())){
            log.error("productName is blank");
            throw new CouponException(ErrorTypes.BLANK_PRODUCT_NAME);
        }
        if(product.getProductCategory() == null){
            log.error("productCategory is invalid");
            throw new CouponException(ErrorTypes.INVALID_PRODUCT_CATEGORY);
        }
        if(product.getPrice() <= 0){
            log.error("price is invalid");
            throw new CouponException(ErrorTypes.INVALID_PRICE);
        }
    }
}
