package com.example.shopbook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    //Hiển thị tất cả các category
    @GetMapping("") //http://localhost:8088/api/v1/categories
    public ResponseEntity<String> getAllCategories(){
        return ResponseEntity.ok("Chao ban");
    }

    @PostMapping("")
    public ResponseEntity<String> insertCategory(){
        return ResponseEntity.ok("This is insertCategory");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id){
        return ResponseEntity.ok("This is insertCategory = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok("deleteCategory with id = " + id);
    }
}
