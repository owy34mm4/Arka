package com.arka.shopingCart.infrastructure.persistence.repository.internal.gateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arka.shopingCart.infrastructure.persistence.entity.ShopingCartTable;

public interface IJPAShopingCart extends JpaRepository<ShopingCartTable,Long>{

    @Query("""  
    select c.id,  
           c.createdAt,  
           u.id,  
           u.firstName,  
           u.firstSurname,  
           u.email,  
           d.productId  
    from ShopingCartTable c  
    join UserTable u on u.id = c.ownerId  
    join c.details d  
    where c.isOrdered = false  
      and c.createdAt < :daysAgo 
    order by c.createdAt asc  
    """)  
Optional<List<Object[]>> findOldNotOrderedCartsRaw(@Param("daysAgo") LocalDateTime daysAgo);
    
}
