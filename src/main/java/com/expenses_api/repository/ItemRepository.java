// package com.expenses_api.repository;

// public class ItemRepository {

// }
package com.expenses_api.repository;

import com.expenses_api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
    @Query("SELECT i FROM Item i WHERE YEAR(i.date) = :y AND MONTH(i.date) = :m")
    List<Item> findByYearAndMonth(
            @Param("y") int year,
            @Param("m") int month);

    @Query("SELECT i FROM Item i WHERE YEAR(i.date) = :y AND MONTH(i.date) = :m AND DAY(i.date) = :d")
    List<Item> findByYearAndMonthAndDay(
            @Param("y") int year,
            @Param("m") int month,
            @Param("d") int day);
}
