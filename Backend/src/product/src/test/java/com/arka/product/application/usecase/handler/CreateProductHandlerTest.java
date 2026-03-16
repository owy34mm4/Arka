package com.arka.product.application.usecase.handler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arka.product.application.port.out.IProductHistoryRepositoryPort;
import com.arka.product.application.port.out.IProductRepositoryPort;
import com.arka.product.application.useCase.command.CreateProductCommand;
import com.arka.product.application.useCase.handler.CreateProductHandler;
import com.arka.product.domain.model.Product;
import com.arka.product.domain.model.ProductHistory;
import com.arka.shared.application.ports.out.category.CategoryInfo;
import com.arka.shared.application.ports.out.category.ICategoryDataPort;
import com.arka.shared.application.ports.out.security.IAuthenticateUserPort;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

@ExtendWith(MockitoExtension.class)
public class CreateProductHandlerTest {

    @Mock
    private ICategoryDataPort categoryRepository;
    
    @Mock
    private IProductRepositoryPort productRepository ;

    @Mock
    private IProductHistoryRepositoryPort productHistoryRepository;

    @Mock
    private IUserDataPort userRepository;
    
    @Mock
    private IAuthenticateUserPort authenticateUserPort;

    @InjectMocks
    private CreateProductHandler createProductHandler;

    //Data reutilizable
    private CreateProductCommand validCommand;
    private UserInfo adminUser;  
    private UserInfo clientUser;
    private CategoryInfo validCategory;

    @BeforeEach
    void setUp(){
        validCommand = CreateProductCommand.builder()  
            . id(1L)
            .name("ProductoValido")
            .description("Descripcion Valida Tambien")
            .price(3000)
            .stock(100)
            .categories(List.of(1L))
            .build();  
  
        adminUser = UserInfo.builder()  
            .id(1L)  
            .role(Roleinfo.Administrador)  
            .build();  
  
        clientUser = UserInfo.builder()  
            .id(2L)  
            .role(Roleinfo.Cliente)  
            .build();

        validCategory = CategoryInfo.builder()
            .id(1L)
            .name("NombreValido")
        .build();

    }

// --- Test 1 ---- HappyPath
    @Test
    @DisplayName("Should Create Product When User is Admin, and data is valid ")
    void should_create_product_successfully(){
        when(authenticateUserPort.getUserId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(adminUser);

        when(categoryRepository.findAllById(validCommand.getCategories()))
            .thenReturn(List.of(validCategory));

        when(productRepository.save(any(Product.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));
        
        when(productHistoryRepository.save(any(ProductHistory.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Product result = assertDoesNotThrow(()->createProductHandler.execute(validCommand));

        assertNotNull(result);
        
        verify(productRepository,times(1)).save(any(Product.class));
        verify(productHistoryRepository, times(1)).save(any(ProductHistory.class));


    }

// --- Test 2 ---- Usuario Sin permisos
    @Test
    @DisplayName("Should thrown when requester is not Employee or Admin")
    void should_thrown_when_requester_isnt_authorized(){
        when(authenticateUserPort.getUserId()).thenReturn(2L);
        when(userRepository.findById(2L)).thenReturn(clientUser);

        assertThrows(BusinessRuleException.class, (()->createProductHandler.execute(validCommand)));

        verify(productRepository, never()).save(any());
        verify(productHistoryRepository, never()).save(any());

    }


// --- Test 3 --- Orden de Validaciones
    @Test
    @DisplayName("Should check permissions before any other action")
    void should_validate_permissions_before_any_action(){
        when(authenticateUserPort.getUserId()).thenReturn(2L);
        when(userRepository.findById(2L)).thenReturn(clientUser);

        assertThrows(BusinessRuleException.class, (()->createProductHandler.execute(validCommand)));

        verify(categoryRepository, never()).findAllById(anyList());
    }


}
