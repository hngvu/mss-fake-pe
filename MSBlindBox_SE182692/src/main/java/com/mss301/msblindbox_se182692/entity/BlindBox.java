package com.mss301.msblindbox_se182692.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate releaseDate;
    @Min(value = 1, message = "stock must be greater or equal 1")
    @Max(value = 100, message = "stock must be lower or equal 100")
    int stock;

    @NotNull(message = "brand id required")
    int brandId;

    @NotNull(message = "category id required")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
