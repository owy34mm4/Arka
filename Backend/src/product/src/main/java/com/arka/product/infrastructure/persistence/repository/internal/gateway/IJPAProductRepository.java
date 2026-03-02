package com.arka.product.infrastructure.persistence.repository.internal.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arka.product.infrastructure.persistence.entity.ProductTable;


public interface IJPAProductRepository extends JpaRepository<ProductTable,Long>{

    @Query("""  
        select p.id, p.name, p.stock  
        from ProductTable p  
        where p.stock < :threshold  
        order by p.stock asc  
        """)  
    Optional<List<Object[]>> findProductsBelowStock(@Param("threshold") Integer threshold);
}
