// package com.expenses_api.controller;

// public class ItemController {

// }

// package com.expenses_api.controller;

// import com.expenses_api.model.Item;
// import com.expenses_api.repository.ItemRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/items")
// public class ItemController {

//     @Autowired
//     private ItemRepository itemRepository;

//     @GetMapping
//     public List<Item> getAllItems() {
//         return itemRepository.findAll();
//     }

//     @PostMapping
//     public Item createItem(@RequestBody Item item) {
//         return itemRepository.save(item);
//     }
// }

package com.expenses_api.controller;

import com.expenses_api.model.Item;
import com.expenses_api.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // GET /api/items?year=2025&month=6 のように受け取る

    @GetMapping
    public List<Item> getItems(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day) {

        if (year != null && month != null && day != null) {
            return itemRepository.findByYearAndMonthAndDay(year, month, day);
        }
        return itemRepository.findAll();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        // idがnullまたは空ならUUIDをセット
        if (item.getId() == null || item.getId().isEmpty()) {
            item.setId(UUID.randomUUID().toString());
        }
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 削除成功時は204 No Contentを返す
        } else {
            return ResponseEntity.notFound().build(); // アイテムが見つからない場合は404 Not Foundを返す
        }
    }
}
