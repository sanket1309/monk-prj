package com.example.monk_prj.repository;

import com.example.monk_prj.model.Product;
import com.example.monk_prj.model.coupon.Coupon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    void save(Product product);
    Product get(String id);
    List<Product> getAll();
    void remove(String id);
}
