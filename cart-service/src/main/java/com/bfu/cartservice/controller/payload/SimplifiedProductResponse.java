package com.bfu.cartservice.controller.payload;

import java.math.BigDecimal;

public record SimplifiedProductResponse(
        String productId,
        String name,
        BigDecimal price
) {
}
