package com.myshopping.My.Shopping.service;

import com.myshopping.My.Shopping.dto.OrderDetailsDTO;
import com.myshopping.My.Shopping.models.AppUser;
import com.myshopping.My.Shopping.models.OrderTable;
import com.myshopping.My.Shopping.models.Product;
import com.myshopping.My.Shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    public OrderTable placeOrder(List<OrderDetailsDTO> orderDetailsDTOList, UUID userId){
        AppUser user=userService.getUserById(userId);
        OrderTable orderTable=new OrderTable();
        int totalPrice=0;
        int totalQuantity=0;
        List<Product> products=new ArrayList<>();
        for(OrderDetailsDTO order:orderDetailsDTOList){
            UUID productId= order.getId();
            totalQuantity+=order.getQuantity();
            Product product=productService.getProductById(productId);
            totalPrice+=(order.getQuantity()*product.getPrice());
            products.add(product);
            orderTable.setPaymentMethod(order.getPaymentMode());
        }
        orderTable.setProducts(products);
        orderTable.setTotalPrice(totalPrice);
        orderTable.setTotalQuantity(totalQuantity);
        orderTable.setStatus("Not Delivered");
        orderTable.setUser(user);
        orderRepository.save(orderTable);
        return orderTable;
    }


}
