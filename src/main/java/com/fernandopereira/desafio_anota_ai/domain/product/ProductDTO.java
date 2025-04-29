package com.fernandopereira.desafio_anota_ai.domain.product;

public record ProductDTO(
         Long id,

         String title,

         String ownerId,

         String category,

         Integer price,

         String description
) {
}
