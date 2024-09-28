package com.example.monk_prj.repository.jsonmodel;

import com.example.monk_prj.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {
    List<Product> products;
}
