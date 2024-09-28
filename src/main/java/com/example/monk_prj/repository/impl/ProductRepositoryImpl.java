package com.example.monk_prj.repository.impl;

import com.example.monk_prj.model.Product;
import com.example.monk_prj.repository.ProductRepository;
import com.example.monk_prj.repository.jsonmodel.ProductData;
import com.example.monk_prj.utility.JsonMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Data
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    ResourceLoader resourceLoader;

    private Map<String, Product> products = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:json/products.json");

        // Convert Resource to Path and read content
        Path filePath = resource.getFile().toPath();
        String json = Files.readString(filePath);
        ProductData productData = JsonMapper.mapJsonToObject(json, ProductData.class);
        products = productData.getProducts().stream().collect(Collectors.toMap(it -> it.getProductId().getId(), it -> it));
    }
    @Override
    public void save(Product product){
        products.put(product.getProductId().getId(), product);
    }
    @Override
    public Product get(String productId){
        return products.get(productId);
    }
    @Override
    public List<Product> getAll(){
        return new ArrayList<>(products.values());
    }

    @Override
    public void remove(String id) {
        products.remove(id);
    }
}
