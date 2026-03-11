package com.arka.e2e.scenarios;

import static org.junit.Assert.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.ResponseCreateCategory;
import com.arka.e2e.BaseE2E;

public class CategoryE2E extends BaseE2E {

    void admin_and_employe_can_create_category(){
        //Login
        String token = loginInAdmin();

        //Actua
        ResponseCreateCategory response = createCategory(token, "Categoria Nueva").getBody();

        assertNotNull(response.getId());

        // // 3. Verificar que aparece en la lista pública  
        // List<ResponseProduct> list = getAllProducts();  
        // assertTrue(list.stream().anyMatch(p -> p.getName().equals("Macbook Pro")));
    }
    
}
