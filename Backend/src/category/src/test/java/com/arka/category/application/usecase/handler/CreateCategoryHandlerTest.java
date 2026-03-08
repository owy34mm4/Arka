package com.arka.category.application.usecase.handler;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;   
import static org.junit.jupiter.api.Assertions.assertNotNull;  
import static org.junit.jupiter.api.Assertions.assertThrows;   
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arka.category.application.port.out.ICategoryRepository;
import com.arka.category.application.usecase.command.CreateCategoryCommand;
import com.arka.category.domain.model.Category;
import com.arka.category.domain.valueObject.CategoryName;
import com.arka.shared.application.ports.out.security.IAuthenticateUserPort;
import com.arka.shared.application.ports.out.user.IUserDataPort;
import com.arka.shared.application.ports.out.user.Roleinfo;
import com.arka.shared.application.ports.out.user.UserInfo;
import com.arka.shared.domain.exceptions.BusinessRuleException;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryHandlerTest {

    @Mock
    private ICategoryRepository categoryRepository; 

    @Mock  
    private IAuthenticateUserPort authenticatedUser;
    
    @Mock
    private IUserDataPort userRepository;

    @InjectMocks
    private CreateCategoryHandler createCategoryHandler;

    // ── Datos reutilizables ─────────────────────────────────────────────  
    private CreateCategoryCommand validCommand;  
    private UserInfo adminUser;  
    private UserInfo clientUser;

    @BeforeEach
    void setUp(){
        validCommand = CreateCategoryCommand.builder()  
            .name("Electronics")  
            .build();  
  
        adminUser = UserInfo.builder()  
            .id(1L)  
            .role(Roleinfo.Administrador)  
            .build();  
  
        clientUser = UserInfo.builder()  
            .id(2L)  
            .role(Roleinfo.Cliente)  
            .build();
    }

//--Test 1: HappyPath ------
    @Test
    @DisplayName("Should create category when user is Admin and category does not exist")
    void should_create_category_sucesfully(){
        when(authenticatedUser.getUserId()).thenReturn(1L);  
        when(userRepository.findById(1L)).thenReturn(adminUser);  
        when(categoryRepository.existsByName("Electronics")).thenReturn(false);  
        when(categoryRepository.save(any(Category.class)))  
            .thenAnswer(invocation -> invocation.getArgument(0));


        Category result = createCategoryHandler.execute(validCommand);

        assertNotNull(result);
        assertEquals(validCommand.getName(), result.getName().getValue());

        verify(categoryRepository,times(1)).save(any(Category.class));
        verify(categoryRepository,times(1)).existsByName(validCommand.getName());

    }

    // ── Test 2: Cliente sin permisos ────────────────────────────────────  
    @Test  
    @DisplayName("Should throw BusinessRuleException when user role is Cliente")  
    void should_throw_when_user_is_cliente() {  
        // ARRANGE  
        when(authenticatedUser.getUserId()).thenReturn(2L);  
        when(userRepository.findById(2L)).thenReturn(clientUser);  
  
        // ACT & ASSERT  
        assertThrows(BusinessRuleException.class, (()-> createCategoryHandler.execute(validCommand)));
  
        // Verificamos que NUNCA llegó a persistir  
        verify(categoryRepository, never()).save(any());  
        verify(categoryRepository, never()).existsByName(any());  
    }

    // ── Test 3: Categoría duplicada ─────────────────────────────────────  
    @Test  
    @DisplayName("Should throw BusinessRuleException when category already exists")  
    void should_throw_when_category_already_exists() {  
        // ARRANGE  
        when(authenticatedUser.getUserId()).thenReturn(1L);  
        when(userRepository.findById(1L)).thenReturn(adminUser);  
        when(categoryRepository.existsByName("Electronics")).thenReturn(true);  
  
        // ACT & ASSERT  
       assertThrows(BusinessRuleException.class, (()-> createCategoryHandler.execute(validCommand))) ;
  
        // Verificamos que NUNCA intentó persistir  
        verify(categoryRepository, never()).save(any());  
    }
    
    // ── Test 4: Verificar que retorna la categoría persistida ───────────  
    @Test  
    @DisplayName("Should return the persisted category with its generated ID")  
    void should_return_persisted_category_with_id() {  
        // ARRANGE  
        Category savedCategory = Category.builder()  
            .id(10L)  
            .name(CategoryName.create("Electronics"))
            .build();  
  
        when(authenticatedUser.getUserId()).thenReturn(1L);  
        when(userRepository.findById(1L)).thenReturn(adminUser);  
        when(categoryRepository.existsByName("Electronics")).thenReturn(false);  
        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);  
  
        // ACT  
        Category result = createCategoryHandler.execute(validCommand);  
  
        // ASSERT  
        assertEquals(10L, result.getId());
        assertEquals(validCommand.getName(), result.getName().getValue());
    }

    // ── Test 5: Verificar orden de validaciones ─────────────────────────  
    @Test  
    @DisplayName("Should check permissions BEFORE checking category existence")  
    void should_validate_permissions_before_checking_existence() {  
        // ARRANGE  
        when(authenticatedUser.getUserId()).thenReturn(2L);  
        when(userRepository.findById(2L)).thenReturn(clientUser);  
  
        // ACT  
        assertThrows(BusinessRuleException.class,(()->createCategoryHandler.execute(validCommand)));
  
        // ASSERT - existsByName nunca debería llamarse si no tiene permisos  
        verify(categoryRepository, never()).existsByName(any());  
    }
}
