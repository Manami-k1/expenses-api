package com.expenses_api.model;

import java.util.UUID;

import jakarta.persistence.*;
// import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// import java.util.List;
// import java.util.UUID;
// import java.util.ArrayList; // ← 追加
// // import java.time.LocalDate;

// @Data
// @Entity
// public class Category {

//     @Id
//     @GeneratedValue(strategy = GenerationType.UUID)
//     private String id;

//     private String name;

// }

// @Entity
// @Data
// public class Category {

//     @Id
//     @GeneratedValue(strategy = GenerationType.UUID)
//     private String id;

//     private String name;

//     @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<Item> items = new ArrayList<>();

//     public void addItem(Item item) {
//         items.add(item);
//         item.setCategory(this);
//     }

//     public void removeItem(Item item) {
//         items.remove(item);
//         item.setCategory(null);
//     }
// }

// @Entity
// @Table(name = "categories")
// public class Category {
//     @Id
//     private UUID id;

//     private String name;

//     // アイテムとの関係はここでは持たなくてOK（双方向にしたい場合は @OneToMany もつける）
// }

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    // @Id
    // private String id;

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @NotNull
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull
    @NotBlank(message = "name must not be blank")
    private String color;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
