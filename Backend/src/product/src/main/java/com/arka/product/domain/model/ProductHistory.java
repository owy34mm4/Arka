package com.arka.product.domain.model;

import java.util.Date;
import java.util.List;

import com.arka.product.domain.valueObjects.ProductCategoriesIds;
import com.arka.product.domain.valueObjects.ProductDescription;
import com.arka.product.domain.valueObjects.ProductName;
import com.arka.product.domain.valueObjects.ProductPrice;
import com.arka.product.domain.valueObjects.ProductStock;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.application.ports.out.user.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductHistory {
    
    private Long id;

    private ProductName name;

    private ProductDescription description;

    private ProductPrice price;

    private ProductStock stock;

    private List<CategoryInfo> categories;

    private ProductCategoriesIds categoriesIds;

    private List<Long> shopingCartsIds;

    private List<Long> ordersIds;

    private Date createdAt;

    private Long createdById;

    private UserInfo createdBy;

    private Date modifiedAt;

    private Long modifiedById; 

    private UserInfo modifiedBy;
    
    private Long productId;

    public Product toProduct (){
        return Product.builder()
        .id(this.getId())
        .name(this.getName())
        .description(this.getDescription())
        .price(this.getPrice())
        .stock(this.getStock())
        .categories(null)
        .categoriesIds(this.getCategoriesIds())
        .shopingCartsIds(this.getShopingCartsIds())
        .ordersIds(this.getOrdersIds())
        .build();

    }
    
}
