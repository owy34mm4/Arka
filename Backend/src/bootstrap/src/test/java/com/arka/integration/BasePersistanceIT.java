package com.arka.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.order.application.port.out.IOrderRepository;
import com.arka.product.application.port.out.IProductHistoryRepositoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.shopingCart.application.port.out.IShopingCartRepository;
import com.arka.user.application.port.out.IUserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Usa el Testcontainer, no H2
@Testcontainers
public class BasePersistanceIT extends AbstractPersistanceIT{
    @Autowired
    protected ICategoryRepository categoryRepository;

    @Autowired
    protected IUserRepository userRepository;

    @Autowired
    protected IProductRepositoryPort productRepository;

    @Autowired
    protected IProductHistoryRepositoryPort productHistoryRepository;

    @Autowired
    protected IOrderRepository orderRepository;

    @Autowired
    protected IShopingCartRepository shopingCartRepository;
}
