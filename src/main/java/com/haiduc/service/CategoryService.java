package com.haiduc.service;

import com.haiduc.model.Category;

public interface CategoryService  {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
}
