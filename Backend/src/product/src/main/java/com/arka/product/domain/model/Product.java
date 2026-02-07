package com.arka.product.domain.model;

import java.util.List;

import com.arka.product.domain.valueObjects.ProductCategoriesIds;
import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.product.domain.valueObjects.ProductName;
import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private ProductName name;
    private ProductDescription description;
    private ProductPrice price;
    private ProductStock stock;
    private List<CategoryInfo> categories;
    private ProductCategoriesIds categoriesIds;
    private List<Long> shopingCartsIds;
    private List<Long> ordersIds;


    public static Product create (Long id,String name,String description,Integer price,
                                Integer stock,List<Long> categoriesIds, List<CategoryInfo> categories ,
                                List<Long> shopingCarts,List<Long> orders) throws BusinessRuleException{
        
        return Product.builder()
        .id(id)
        .name(new ProductName(name))
        .description(new ProductDescription(description))
        .price(new ProductPrice(price, "cop"))
        .stock(new ProductStock(stock))
        .categoriesIds(new ProductCategoriesIds(categoriesIds))
        .shopingCartsIds(shopingCarts)
        .ordersIds(orders)
        .build();
    }

   public void inyectCategories( List<CategoryInfo> categoriesToInyect) throws BusinessRuleException{
        validateCategories(categoriesToInyect);
        this.setCategories(categoriesToInyect);
   }


   public ProductHistory toProductHistory(){
    return ProductHistory.builder()
        .id(null)
        .name(this.getName())
        .description(this.getDescription())
        .price(this.getPrice())
        .stock(this.getStock())
        .productId(this.getId())
        .categoriesIds(this.getCategoriesIds())
        .categories(this.getCategories())
        .shopingCartsIds(this.getShopingCartsIds())
        .ordersIds(this.getShopingCartsIds())
    .build();
   }

    
    
    
    private static void validateCategories(List<CategoryInfo> categoriesToCheck) throws BusinessRuleException{
        if(categoriesToCheck.size()<=0 || categoriesToCheck.isEmpty() || categoriesToCheck==null){
            throw new BusinessRuleException("Invalid Product Categories -> Not allowed");
        }

    }
   



}