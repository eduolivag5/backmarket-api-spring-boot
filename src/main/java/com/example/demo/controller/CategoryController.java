package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCategories(@RequestParam(required = false) Integer id)
    {
        if (id != null){
            Optional<Category> category = categoryService.getCategoryById(id);
            return category.<ResponseEntity<ApiResponse<?>>>map(value -> ResponseEntity.ok(
                    new ApiResponse<>(false, "ok", Map.of(
                            "id", value.getId(),
                            "name", value.getCategory()
                    ))
            )).orElseGet(() -> ResponseEntity.status(404).body(
                    new ApiResponse<>(true, "Category not found.", null)
            ));
        }

        List<Category> categories = categoryService.getAllCategories();
        List<Map<String, Object>> data = new ArrayList<>();
        for (Category c : categories) {
            data.add(Map.of(
                    "id", c.getId(),
                    "name", c.getCategory()
            ));
        }
        return ResponseEntity.ok(new ApiResponse<>(false, "ok", data));
    }

}
