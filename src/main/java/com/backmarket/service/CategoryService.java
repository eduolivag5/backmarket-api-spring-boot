package com.backmarket.service;

import com.backmarket.entity.Category;
import com.backmarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
    }

}
