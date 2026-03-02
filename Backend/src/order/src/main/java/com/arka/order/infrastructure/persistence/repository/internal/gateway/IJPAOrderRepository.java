package com.arka.order.infrastructure.persistence.repository.internal.gateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.arka.order.infrastructure.persistence.entity.OrderTable;
import com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopCustomerWeeklyDTO;



public interface IJPAOrderRepository extends JpaRepository<OrderTable,Long>{

    @Query("SELECT o FROM OrderTable o " +  
           "WHERE o.createdAt >= :desde " +  
           "AND o.state = 'CONFIRMADO'")
    Optional<List<OrderTable>> getlast7DaysOrders (@Param("desde") LocalDateTime desde);


   @Query(value = """  
    SELECT   
        p.id AS productoId,  
        p.name AS nombre,  
        COUNT(od.product_id) AS totalVendido  
    FROM orders o  
    JOIN order_detail od ON od.order_id = o.id  
    JOIN products p ON p.id = od.product_id  
    WHERE o.created_at BETWEEN :fechaInicio AND :fechaFin  
      AND o.state IN ('CONFIRMADO', 'EN_DESPACHO', 'ENTREGADO')  
    GROUP BY p.id, p.name  
    ORDER BY totalVendido DESC  
    """,  
    countQuery = "SELECT 1",  
    nativeQuery = true) 
Optional<List<Object[]>> topMasVendido(  
    @Param("fechaInicio") LocalDateTime fechaInicio,   
    @Param("fechaFin") LocalDateTime fechaFin,   
    PageRequest pageable);


        @Query("""
    select new com.arka.order.infrastructure.persistence.repository.auxiliarObjects.TopCustomerWeeklyDTO(
        u.id, u.firstName, u.firstSurname, u.lastName, u.lastSurname,
        count(o.id),
        coalesce(sum(o.price), 0)
    )
    from OrderTable o
    join UserTable u on u.id = o.ownerId
    where o.createdAt >= :from and o.createdAt < :to
    group by u.id, u.firstName, u.firstSurname, u.lastName, u.lastSurname
    order by count(o.id) desc, coalesce(sum(o.price), 0) desc
    """)
    Optional<Page<TopCustomerWeeklyDTO>> topCustomerOfWeek(@Param("from")LocalDateTime from, @Param("to")LocalDateTime to, Pageable pageable);


}

