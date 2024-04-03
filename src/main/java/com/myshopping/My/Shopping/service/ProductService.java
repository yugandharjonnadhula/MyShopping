package com.myshopping.My.Shopping.service;

import com.myshopping.My.Shopping.dto.ProductDTO;
import com.myshopping.My.Shopping.exceptions.ResourceNotFound;
import com.myshopping.My.Shopping.exceptions.UnAuthorized;
import com.myshopping.My.Shopping.models.AppUser;
import com.myshopping.My.Shopping.models.Product;
import com.myshopping.My.Shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    UserService userService;
    public Product getProductById(UUID id){
        return productRepository.findById(id).orElse(null);
    }
    public List<ProductDTO> convertProductToProductDTO(List<Product> products){
        List<ProductDTO> productList=new ArrayList<>();
        for(Product product:products){
            ProductDTO productDTO=new ProductDTO();
            productDTO.setProductName(product.getName());
            productDTO.setProductCategory(product.getCategory());
            productDTO.setId(product.getId());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setRating(product.getRating());
            productDTO.setSellerName(product.getUser().getName());
            productList.add(productDTO);

        }
        return productList;
    }
    public List<ProductDTO>searchByProductName(String productName){
        List<Product> products=productRepository.getProductByName(productName);
        List<ProductDTO> productDTOList=convertProductToProductDTO(products);
        return productDTOList;
    }
    public List<ProductDTO> searchByCategory(String category){
        List<Product> products =  productRepository.getProductsByCategory(category);
        List<ProductDTO> productList = convertProductToProductDTO(products);
        return productList;
    }
    public List<ProductDTO> searchByCategoryAndProductName(String productName, String category){
        List<Product> products = productRepository.getProductByCategoryAndName(category, productName);
        List<ProductDTO> productList = convertProductToProductDTO(products);
        return productList;
    }
    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productList = convertProductToProductDTO(products);
        return productList;
    }
    public void registerProduct(Product product, UUID sellerId){
        // we need to check this userId exists in our system or not
        // if userId exists what kind of userId it is
        // if it is seller kind of id then it is fine
        // else we need to throw exception
        // With the help of seller id we can get userObject or seller object
        // 1. Get user object by Id;
        AppUser user = userService.getUserById(sellerId);
        if(user == null){
            // user does not exist in our application
            throw new ResourceNotFound(String.format("Seller with sellerId %s does not exist in system.", sellerId.toString()));
        }

        if(!user.getUserType().equals("SELLER")){
            // If user is not of type SELLER -> We are going to throw unAuthorized exception
            throw new UnAuthorized(String.format("User with id %s does not have access to perform this operation", sellerId.toString()));
        }

        product.setUser(user);
        productRepository.save(product);
    }
}
