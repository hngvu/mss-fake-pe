package com.mss301.msblindbox_se182692.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "MSS301Summer25DBBlindBox")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlindBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String rarity;
    double price;
    LocalDate releaseDate;
    int stock;

    int brandId;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
