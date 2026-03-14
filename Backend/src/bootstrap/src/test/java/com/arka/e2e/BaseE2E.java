package com.arka.e2e;

import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.testcontainers.junit.jupiter.Testcontainers;

import com.arka.bootstrap.ArkaAplication;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.RequestCreateCategory;
import com.arka.category.infrastructure.entryPoints.rest.dto.useCase.createCategory.ResponseCreateCategory;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.RequestModifyOrderByCustomer;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.RequestUpdateOrderStatus;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.ResponseModifyOrderByCustomer;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.ModifyOrder.ResponseUpdateOrderStatus;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.RequestRegisterOrder;
import com.arka.order.infrastructure.entryPoints.rest.dto.useCase.RegisterOrder.ResponseRegisterOrder;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct.RequestCreateProduct;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.CreateProduct.ResponseCreateProduct;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock.RequestUpdateStockProduct;
import com.arka.product.infrastructure.entryPoints.rest.dto.useCase.UpdateStock.ResponseUpdateStockProduct;
import com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart.RequestSaveCart;
import com.arka.shopingCart.infrastructure.entryPoint.rest.dto.useCase.saveCart.ResponseSaveCart;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.RequestCreateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.CreateUser.ResponseCreateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser.RequestUpdateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.EditUser.ResponseUpdateUser;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.RequestLogin;
import com.arka.user.infrastructure.entryPoints.rest.dto.useCase.LogIn.ResponseLogIn;


@SpringBootTest(  
    classes = ArkaAplication.class, // La clase principal del monolito  
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT  
)  
@Testcontainers  
public abstract class BaseE2E extends AbstractE2E{  

    // ─── USER ────────────────────────────────────────────────  
    protected ResponseEntity<ResponseCreateUser> createUser(String username, String password) {  
        RequestCreateUser req = RequestCreateUser.builder()  
            .first_name("Test")  
            .last_name("User") 
            .first_surname("Testertor")
            .last_surname("userton")
            .email(username + "@test.com") 
            .username(username)  
            .password(password)               
            .build();  
  
        return restTemplate.postForEntity(  
            "/api/v0/user/create",  
            new HttpEntity<>(req, jsonHeaders()),  
            ResponseCreateUser.class  
        );  
    }  
  
    protected ResponseEntity<ResponseUpdateUser> updateUser(String token, RequestUpdateUser request){
        return restTemplate.exchange("api/v0/user/update",
        HttpMethod.PUT,
        new HttpEntity<>(request,authHeaders(token)),
        ResponseUpdateUser.class);
    }

    // ─── AUTH ────────────────────────────────────────────────  
    protected String login(String username, String password) {  
        RequestLogin req = new RequestLogin(username, password);  
  
        ResponseEntity<ResponseLogIn> response = restTemplate.postForEntity(  
            "/api/v0/auth/login",  
            new HttpEntity<>(req, jsonHeaders()),  
            ResponseLogIn.class  
        );  
  
        return response.getBody().getToken(); // Devuelve el JWT  
    }  

    protected String loginInAdmin(){
        return login("admin", "admin123"); //<-- Se establecen en el 'init_admin.sql'
    }
    // --- Category -----------
    protected ResponseEntity<ResponseCreateCategory> createCategory(String token, String categoryName){
        RequestCreateCategory request = RequestCreateCategory.builder().name(categoryName).build();

        HttpEntity<RequestCreateCategory> entity = new HttpEntity<>(request, authHeaders(token)); 

        ResponseEntity<ResponseCreateCategory> response = restTemplate.exchange("aspi/v0/vategory/create",
        HttpMethod.POST,
        entity,
        ResponseCreateCategory.class);

        return response;
    }

    // ─── PRODUCT ─────────────────────────────────────────────  

    protected ResponseEntity<ResponseCreateProduct> createProduct(String token, RequestCreateProduct request){

        HttpEntity<RequestCreateProduct> entity = new HttpEntity<>(request, authHeaders(token)); 
        
        ResponseEntity<ResponseCreateProduct> response = restTemplate.exchange(
            "api/v0/product/create",
            HttpMethod.POST,
            entity,
            ResponseCreateProduct.class
        );

        return response;
    }

    protected ResponseEntity<ResponseUpdateStockProduct> updateProductStock(String token, Long productId, Integer newStock){
        RequestUpdateStockProduct request = RequestUpdateStockProduct.builder().product_id(productId).new_stock(newStock).build();

        return restTemplate.exchange("api/v0/product/updateStock",
        HttpMethod.PUT,
        new HttpEntity<>(request, authHeaders(token)),
        ResponseUpdateStockProduct.class);

    }

    // --- ShopingCart --------------
    protected ResponseEntity<ResponseSaveCart> createShopingCart(String token, List<Long>productIds){
        RequestSaveCart request = RequestSaveCart.builder().products_ids(productIds).build();

        return restTemplate.exchange("api/v0/ShopingCart/save",
        HttpMethod.POST,
        new HttpEntity<>(request,authHeaders(token)),
        ResponseSaveCart.class
    );
        
    }
    
    // ─── ORDER ───────────────────────────────────────────────  
    protected ResponseEntity<ResponseRegisterOrder> createOrder(String token, Long shopingCartId) {  
        RequestRegisterOrder req = RequestRegisterOrder.builder()  
            .shoping_cart_id(shopingCartId)
            .build();  
  
        return restTemplate.exchange(  
            "/api/v0/orders",  
            HttpMethod.POST,  
            new HttpEntity<>(req, authHeaders(token)),  
            ResponseRegisterOrder.class  
        );  
    }  
  
    protected ResponseEntity<ResponseUpdateOrderStatus> updateOrderStatus (String token, RequestUpdateOrderStatus request){
        return restTemplate.exchange("api/v0/order/modifyStatus",
        HttpMethod.PATCH,
        new HttpEntity<>(request, authHeaders(token)),
        ResponseUpdateOrderStatus.class
    );

    }

    protected ResponseEntity< ResponseModifyOrderByCustomer> modifyOrderByCustomer (String token, RequestModifyOrderByCustomer request){
        return restTemplate.exchange("api/v0/order/modifyByCustomer",
        HttpMethod.PUT,
        new HttpEntity<>(request,authHeaders(token)),
        ResponseModifyOrderByCustomer.class
    );
        
    }
    
    // ─── HELPERS ─────────────────────────────────────────────  
    protected HttpHeaders jsonHeaders() {  
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.APPLICATION_JSON);  
        return headers;  
    }  
  
    protected HttpHeaders authHeaders(String token) {  
        HttpHeaders headers = jsonHeaders();  
        headers.setBearerAuth(token); // "Authorization: Bearer <token>"  
        return headers;  
    }  
}
