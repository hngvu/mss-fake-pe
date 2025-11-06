package com.mss301.msbrand_se18262.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record BlindBoxRequest(
    @Length(min = 11, message = "name must be greater than 10 characters")
    String name,
    @NotNull(message = "rarity required")
    String rarity,
    @NotNull(message = "price required")
    double price,
    @Min(value = 1, message = "stock must be greater or equal 1")
    @Max(value = 100, message = "stock must be lower or equal 100")
    @NotNull(message = "stock required")
    int stock,
    @NotNull(message = "category id required")
    int categoryId,
    @NotNull(message = "brand id required")
    int brandId
) {
}
