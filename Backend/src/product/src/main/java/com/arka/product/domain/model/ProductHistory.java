package com.arka.product.domain.model;

import java.util.Date;
import java.util.List;

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

    private String name;

    private String description;

    private Integer price;

    private Integer stock;

    private List<CategoryInfo> categories;

    private List<Long> categoriesIds;

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
