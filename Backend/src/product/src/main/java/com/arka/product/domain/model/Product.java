package com.arka.product.domain.model;


import java.util.List;

import com.arka.category.domain.model.Category;
import com.arka.order.domain.model.Order;
import com.arka.product.domain.exception.InvalidPropertiesGiven;
import com.arka.shopingCart.domain.model.ShopingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private List<Category> categories;
    private List<ShopingCart> shopingCarts;
    private List<Order> orders;


    public static Product create (Long id,String name,String description,Integer price,Integer stock,List<Category> categories,List<ShopingCart> shopingCarts,List<Order> orders) throws InvalidPropertiesGiven{
        validateName(name);
        validateDescription(description);
        validatePrice(price);
        validateStock(stock);

        return Product.builder()
        .id(id)
        .name(name)
        .description(description)
        .price(price)
        .stock(stock)
        .categories(categories)
        .shopingCarts(shopingCarts)
        .orders(orders)
        .build();
    }

    private static void validateName(String nameToCheck) throws InvalidPropertiesGiven {
        if (nameToCheck.isBlank() || nameToCheck==null){
            throw new InvalidPropertiesGiven("Invalid Product Name -> Null || Blank -> Not Allowed");
        }
    }
    private static void validateDescription(String descriptionToCheck) throws InvalidPropertiesGiven{
        if (descriptionToCheck.isBlank() || descriptionToCheck.length()<5){
            throw new  InvalidPropertiesGiven("Invalid Product Description -> Blank || lenght<5 -> Not Allowed");
        }
    }
    private static  void validatePrice(Integer priceToCheck) throws InvalidPropertiesGiven {
        if (priceToCheck<0 || priceToCheck == null){
            throw new InvalidPropertiesGiven("Invalid Product Price -> price<0 -> Not Allowed");
        }
    }
    private static void validateStock(Integer stockToCheck) throws InvalidPropertiesGiven {
        if (stockToCheck<0 || stockToCheck == null){
            throw new InvalidPropertiesGiven("Invalid Product Stock -> stock<0 -> Not Allowed");
        }
    }
   



}
