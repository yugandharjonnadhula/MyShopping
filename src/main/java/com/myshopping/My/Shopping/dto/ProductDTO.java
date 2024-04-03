package com.myshopping.My.Shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    UUID id;
    String productName;
    String productCategory;
    String rating;
    String description;
    String sellerName;
    int price;
}
