package com.backmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backmarket.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    // category IN (...)
    @Query("""
        SELECT p FROM Product p
        WHERE p.category IN :categories
    """)
    List<Product> findByCategories(@Param("categories") List<Integer> categories);

    // tags overlap (PostgreSQL)
    @Query(value = """
        SELECT * FROM products_v2 p
        WHERE p.tags && CAST(:tags AS text[])
    """, nativeQuery = true)
    List<Product> findByTags(@Param("tags") String[] tags);

    // category IN + tags overlap
    @Query(value = """
        SELECT * FROM products_v2 p
        WHERE p.category IN (:categories)
        AND p.tags && CAST(:tags AS text[])
    """, nativeQuery = true)
    List<Product> findByCategoriesAndTags(
            @Param("categories") List<Integer> categories,
            @Param("tags") String[] tags
    );

    @Query("""
        SELECT p FROM Product p
        WHERE LOWER(p.nameShort) LIKE LOWER(CONCAT('%', :nameShort, '%'))
           OR LOWER(p.name)      LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    Optional<Product> findByNameOrNameShort(@Param("name") String name, @Param("nameShort") String nameShort);

}


