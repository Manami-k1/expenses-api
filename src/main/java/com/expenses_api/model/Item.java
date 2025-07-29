package com.expenses_api.model;

import jakarta.persistence.*;
// import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

// @Entity
// @Table(name = "item")
// @Getter
// @Setter
// public class Item {

//     @Id
//     @Column(columnDefinition = "CHAR(36)")
//     private String id;

//     private String name;
//     private int price;
//     private LocalDate date;
//     @Column(name = "category_id")
//     private String categoryId; // FK

//     @PrePersist
//     public void prePersist() {
//         if (this.id == null) {
//             this.id = UUID.randomUUID().toString();
//         }
//     }
// }
@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @NotNull
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotNull
    @Min(value = 1, message = "price must be greater than 0")
    private int price;

    @NotNull
    private LocalDate date;

    @Column(name = "category_id", columnDefinition = "CHAR(36)")
    private String categoryId;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        if (this.categoryId == null) {
            this.categoryId = "00000000-0000-0000-0000-000000000999"; // または "00000000-0000-0000-0000-000000000000"
                                                                      // などの固定UUID
        }
    }
}
