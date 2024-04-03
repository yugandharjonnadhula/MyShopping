package com.myshopping.My.Shopping.controller;

import com.myshopping.My.Shopping.dto.ProductDTO;
import com.myshopping.My.Shopping.models.AppUser;
import com.myshopping.My.Shopping.service.ProductService;
import com.myshopping.My.Shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class CommonController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @PostMapping("/user/register")
    public String registerUser(@RequestBody AppUser appUser){
        userService.registerUser(appUser);
        return "User got successfully saved";
    }
   @GetMapping("/product/search")
    public List<ProductDTO> searchProductByCategory(@RequestParam(required = false) String category,@RequestParam(required = false) String productName){
        if(category != null && productName!=null ){
            return productService.searchByCategoryAndProductName(category,productName);
        }
        else if(category!=null){
            return productService.searchByCategory(category);
        }
        else if(productName!=null) {
            return productService.searchByProductName(productName);
        } else {
            return productService.getAllProducts();
        }
    }
}
