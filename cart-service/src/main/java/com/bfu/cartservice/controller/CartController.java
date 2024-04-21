package com.bfu.cartservice.controller;

import com.bfu.cartservice.client.CatalogueClient;
import com.bfu.cartservice.controller.payload.ArrayOfSimplifiedProduct;
import com.bfu.cartservice.controller.payload.SimplifiedProductResponse;
import com.bfu.cartservice.entity.Product;
import com.bfu.cartservice.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CatalogueClient client;

    @GetMapping("cart")
    public ArrayOfSimplifiedProduct getCart(Principal principal) {
        String userId = ((JwtAuthenticationToken) principal).getToken().getSubject();
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/cart/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable String productId, Principal principal) throws ExecutionException, InterruptedException {
        String userId = ((JwtAuthenticationToken) principal).getToken().getSubject();
        SimplifiedProductResponse cartProductResponse = client.getProductInfo(productId);

        Product product = Product.builder()
                .id(cartProductResponse.productId())
                .name(cartProductResponse.name())
                .price(cartProductResponse.price())
                .quantity(1).build();
        cartService.addToCart(userId, product);
        return ResponseEntity.ok("Product added to cart");
    }


    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable String productId, Principal principal) {
        String userId = ((JwtAuthenticationToken) principal).getToken().getSubject();
        cartService.deleteProductFromCart(userId, productId);
        return ResponseEntity.ok("Product deleted from cart");
    }

    @DeleteMapping("/cart")
    public ResponseEntity<?> deleteAllProducts(Principal principal) {
        String userId = ((JwtAuthenticationToken) principal).getToken().getSubject();
        cartService.deleteAllProducts(userId);
        return ResponseEntity.ok("Cart cleared");
    }
}
