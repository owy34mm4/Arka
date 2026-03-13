package com.arka.product.application.usecase.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
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
import com.arka.product.application.useCase.command.UpdateStockProductCommand;
import com.arka.product.application.useCase.handler.UpdateStockProductHandler;
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
public class UpdateStockProductHandlerTest {
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
    private UpdateStockProductHandler handler;

    // Data Tests

    private UpdateStockProductCommand validCommand;
    private UserInfo adminUser;  
    private UserInfo clientUser;
    private CategoryInfo validCategory;
    private Product validProduct;

    @BeforeEach
    void setUp(){
        validCommand = UpdateStockProductCommand.builder()  
            .productId(1L)
            .newStock(30)
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

        validProduct = Product.create(
            1L, 
            "Halls", 
            "Barra de 10 Unidades",
            2000, 
            50, 
            List.of(2L), 
            null, 
            null, 
            null
        );

    }

// --- Test 1 Happy Path --- Admin updates an existing product with a valid stock

    @Test
    @DisplayName("Should create succesfully when requester is admin,product exists, and stock is valid")
    void should_update_stock_sucessfully(){

        when(authenticateUserPort.getUserId())
            .thenReturn(1L);

        when(userRepository.findById(1L))
            .thenReturn(adminUser);

        when(productRepository.findById(1L))
            .thenReturn(validProduct);

        when(productRepository.save(any(Product.class)))
            .thenAnswer(invocation-> invocation.getArgument(0));

        when(productHistoryRepository.save(any(ProductHistory.class)))
            .thenAnswer(invocation-> invocation.getArgument(0));

        when(categoryRepository.findAllById(anyList()))
            .thenReturn(List.of(validCategory));

        Product result = assertDoesNotThrow(()-> handler.execute(validCommand));

        
        assertAll(
            ()->{
                assertNotNull(result);

                assertEquals(validCommand.getProductId(), result.getId());
                assertEquals(validCommand.getNewStock(), result.getStock().getValue());
            }
        );
       
        verify(userRepository,times(1)).findById(anyLong());
        verify(productRepository,times(1)).findById(anyLong());
        verify(productRepository,times(1)).save(any(Product.class));
        verify(productHistoryRepository,times(1)).save(any(ProductHistory.class));

    }

// --- Test 2 Requester sin permisos
    @Test
    @DisplayName("Shpul thrown when requester isnt Admin or Employee")
    void sholud_thrown_when_requester_unauthorized(){
        when(authenticateUserPort.getUserId()).thenReturn(2L);
        when(userRepository.findById(2L)).thenReturn(clientUser);

        assertThrows(BusinessRuleException.class, (()-> handler.execute(validCommand)));

        verify(userRepository,times(1)).findById(anyLong());
        verify(productRepository,never()).findById(anyLong());
        verify(productRepository,never()).save(any());
        verify(productHistoryRepository, never()).save(any());
    }

// --- Test 3 ---- Orden de validacion
    @Test
    @DisplayName("Must check validations before action")
    void should_check_authorization_before_action(){
        when(authenticateUserPort.getUserId()).thenReturn(2L);
        when(userRepository.findById(2L)).thenReturn(clientUser);

        assertThrows(BusinessRuleException.class, (()-> handler.execute(validCommand)));

        verify(userRepository,times(1)).findById(anyLong());
        verify(productRepository,never()).findById(anyLong());
        verify(productRepository,never()).save(any());
        verify(productHistoryRepository, never()).save(any());
    }


}
