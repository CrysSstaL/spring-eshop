package com.bfu.catalogueservice.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(schema = "catalogue", name = "t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_product_id")
    private String productId;

    @Column(name = "c_title")
    private String name;

    @Column(name = "c_price")
    private BigDecimal price;

//    @Column(name = "c_attributes", columnDefinition = "jsonb")
//    @Convert(converter = JsonNodeConverter.class)
//    private JsonNode attributes;

    @ManyToOne
    @JoinColumn(name = "c_category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "c_brand_id")
    private Brand brand;
}
