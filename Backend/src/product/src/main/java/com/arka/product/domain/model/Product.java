package com.arka.product.domain.model;

import java.util.List;

import com.arka.shared.application.ports.out.category.CategoryInfo;

import com.arka.shared.domain.exceptions.InvalidPropertiesGiven;

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
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private List<CategoryInfo> categories;
    private List<Long> categoriesIds;
    private List<Long> shopingCartsIds;
    private List<Long> ordersIds;


    public static Product create (Long id,String name,String description,Integer price,Integer stock,List<Long> categoriesIds, List<CategoryInfo> categories ,List<Long> shopingCarts,List<Long> orders) throws InvalidPropertiesGiven{
        validateName(name);
        validateDescription(description);
        validatePrice(price);
        validateStock(stock);
        validateCategoriesIds(categoriesIds);

        return Product.builder()
        .id(id)
        .name(name)
        .description(description)
        .price(price)
        .stock(stock)
        .categoriesIds(categoriesIds)
        .shopingCartsIds(shopingCarts)
        .ordersIds(orders)
        .build();
    }

   public void inyectCategories( List<CategoryInfo> categoriesToInyect) throws InvalidPropertiesGiven{
        validateCategories(categoriesToInyect);
        this.setCategories(categoriesToInyect);
   };

   public void updateStock(Integer newStock) throws InvalidPropertiesGiven{
    if (newStock <0 || newStock ==null){throw new InvalidPropertiesGiven("Actualziacion de stock fallida, cantidad invalida -> <0");}
    this.setStock(newStock);
   }


   public void checkIstance() throws InvalidPropertiesGiven{
        validateName(this.name);
        validateDescription(this.description);
        validatePrice(this.price);
        validateStock(this.stock);
        validateCategoriesIds(this.categoriesIds);
        
        
   }

   public ProductHistory toProductHistory(){
    return ProductHistory.builder()
        .id(this.getId())
        .name(this.getName())
        .description(this.getDescription())
        .price(this.getPrice())
        .stock(this.getStock())
        .categoriesIds(this.getCategoriesIds())
        .categories(this.getCategories())
        .shopingCartsIds(this.getShopingCartsIds())
        .ordersIds(this.getShopingCartsIds())
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
    
    private static void validateCategoriesIds(List<Long> categoriesIds) throws InvalidPropertiesGiven{
        if (categoriesIds.size()<=0 || categoriesIds.isEmpty() || categoriesIds ==null){
            throw new InvalidPropertiesGiven("Invalid Product CategoriesId -> Not allowed");
        }
    }
    private static void validateCategories(List<CategoryInfo> categoriesToCheck) throws InvalidPropertiesGiven{
        if(categoriesToCheck.size()<=0 || categoriesToCheck.isEmpty() || categoriesToCheck==null){
            throw new InvalidPropertiesGiven("Invalid Product Categories -> Not allowed");
        }

    }
   



}