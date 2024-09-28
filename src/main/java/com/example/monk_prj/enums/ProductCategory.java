package com.example.monk_prj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum ProductCategory {
    FOOD("FOOD"),
    FASHION("FASHION"),
    ELECTRONICS("ELECTRONICS");


    private String name;
}
