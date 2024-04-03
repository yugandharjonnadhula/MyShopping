package com.myshopping.My.Shopping.controller;

import com.myshopping.My.Shopping.dto.OrderDetailsDTO;
import com.myshopping.My.Shopping.models.OrderTable;
import com.myshopping.My.Shopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/buyer")

public class BuyerController {
    @Autowired
    BuyerService buyerService;
    @PostMapping("/placeorder")
    public OrderTable placeOrder(@RequestBody  List<OrderDetailsDTO> orders, @RequestParam  UUID userId){
        return buyerService.placeOrder(orders, userId);
    }
}
