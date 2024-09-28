package com.example.monk_prj.model;

import com.example.monk_prj.enums.CouponType;
import com.example.monk_prj.model.coupon.Coupon;
import com.example.monk_prj.model.coupon.CouponDetails;
import com.example.monk_prj.model.id.CartId;
import com.example.monk_prj.model.id.CouponId;
import com.example.monk_prj.model.id.OrderId;
import com.example.monk_prj.model.id.ProductId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private CartId cartId;
    private List<ProductOrder> cartItems;
    private int total;

    public int getSize(){
        int total = 0;
        for(ProductOrder productOrder : cartItems){
            total += productOrder.getQuantity();
        }
        return total;
    }

    public int generateTotal(){
        int total = 0;
        for(ProductOrder productOrder : cartItems){
            total += productOrder.getTotalPrice();
        }
        this.total += total;
        return total;
    }

    public void addProduct(Product product){
        addProduct(product, 1);
    }

    public void addProduct(Product product, int quantity){
        for(ProductOrder productOrder : cartItems){
            if(Product.equals(product, productOrder.getProduct())){
                productOrder.setQuantity(productOrder.getQuantity() + quantity);
                generateTotal();
                return;
            }
        }
        ProductOrder productOrder = new ProductOrder();
//        productOrder.setOrderId(new OrderId());
        productOrder.setProduct(product);
        productOrder.setQuantity(quantity);
        cartItems.add(productOrder);
        generateTotal();
    }

    public static void validate(Cart cart){
        if(Objects.isNull(cart)){
            log.error("cart is null");
            throw new RuntimeException();
        }

        if(Objects.isNull(cart.getCartId())){
            cart.setCartId(new CartId());
        }
        CouponId.validateId(cart.getCartId());
        if(CollectionUtils.isEmpty(cart.getCartItems())){
            log.error("Cart is empty");
            throw new RuntimeException();
        }
        for(ProductOrder productOrder : cart.getCartItems()){
            ProductOrder.validate(productOrder);
        }
        cart.generateTotal();
    }
}
