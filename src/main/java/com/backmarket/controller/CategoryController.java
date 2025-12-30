package com.backmarket.controller;

import com.backmarket.dto.ApiResponseDto;
import com.backmarket.entity.Category;
import com.backmarket.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Tag(name="Categories")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /* GET */
    @Operation(
            summary = "Get categories",
            description = "Retrieve all categories or a single category by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully."),
            @ApiResponse(responseCode = "404", description = "Category not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getCategories(
            @RequestParam(required = false) Integer id
    ) {
        if (id != null) {
            Optional<Category> category = categoryService.getCategoryById(id);

            return category.<ResponseEntity<ApiResponseDto<?>>>map(value ->
                    ResponseEntity.ok(
                            new ApiResponseDto<>(false, "OK", Map.of(
                                    "id", value.getId(),
                                    "name", value.getCategory()
                            ))
                    )
            ).orElseGet(() ->
                    ResponseEntity.status(404).body(
                            new ApiResponseDto<>(true, "Category not found.", null)
                    )
            );
        }

        List<Category> categories = categoryService.getAllCategories();
        List<Map<String, Object>> data = new ArrayList<>();

        for (Category c : categories) {
            data.add(Map.of(
                    "id", c.getId(),
                    "name", c.getCategory()
            ));
        }

        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", data)
        );
    }
}
