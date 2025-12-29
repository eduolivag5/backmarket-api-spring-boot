package com.backmarket.service;

import com.backmarket.dto.PriceResponse;
import com.backmarket.dto.ProductResponse;
import com.backmarket.entity.Product;
import com.backmarket.repository.PriceRepository;
import com.backmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceService priceService;

    /* GET */
    public List<ProductResponse> getProductsByFilters(String category, String tags) {

        List<Integer> categories = null;
        String[] tagArray = null;

        if (category != null && !category.isBlank()) {
            categories = Arrays.stream(category.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        }

        if (tags != null && !tags.isBlank()) {
            tagArray = Arrays.stream(tags.split(","))
                    .map(String::trim)
                    .toArray(String[]::new);
        }

        List<Product> products;

        if (categories != null && tagArray != null) {
            products = productRepository.findByCategoriesAndTags(categories, tagArray);
        } else if (categories != null) {
            products = productRepository.findByCategories(categories);
        } else if (tagArray != null) {
            products = productRepository.findByTags(tagArray);
        } else {
            products = productRepository.findAll();
        }

        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public Optional<ProductResponse> getProductById(UUID id) {
        return productRepository.findById(id)
                .map(this::mapToProductResponse);
    }

    public Optional<Product> getProductByNameOrNameShort(String name, String nameShort) {
        return productRepository.findByNameOrNameShort(name, nameShort);
    }

    private ProductResponse mapToProductResponse(Product product) {
        List<PriceResponse> prices =
                priceService.getPricesByProduct(product.getId());

        return new ProductResponse(
                product.getId(),
                product.getCreatedAt(),
                product.getCategory(),
                product.getBrand(),
                product.getNameShort(),
                product.getName(),
                product.getStorages(),
                product.getColors(),
                product.getImages(),
                product.getTags(),
                prices
        );
    }


    /* POST */
    public Product createProduct(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }


    /* PUT */
    public Product updateProduct(Product product) {
        Product existingProduct =  productRepository.findById(product.getId()).get();
        existingProduct.setBrand(product.getBrand());
        existingProduct.setName(product.getName());
        existingProduct.setNameShort(product.getNameShort());
        existingProduct.setStorages(product.getStorages());
        existingProduct.setColors(product.getColors());
        existingProduct.setImages(product.getImages());
        existingProduct.setTags(product.getTags());
        return productRepository.save(existingProduct);
    }


    /* DELETE */
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
