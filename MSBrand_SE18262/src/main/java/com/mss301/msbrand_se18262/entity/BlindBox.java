package com.mss301.msbrand_se18262.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Length(min = 11, message = "name must be greater than 10 characters")
    String name;
    @NotBlank(message = "rarity required")
    String rarity;
    @NotNull(message = "price required")
    double price;
    @NotNull(message = "release date required")
    LocalDate releaseDate;
    @Min(value = 1, message = "stock must be greater or equal 1")
    @Max(value = 100, message = "stock must be lower or equal 100")
    int stock;

    @NotNull(message = "category id required")
    int categoryId;

    @NotNull(message = "brand id required")
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    Brand brand;
}
