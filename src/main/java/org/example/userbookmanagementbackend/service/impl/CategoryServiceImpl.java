package org.example.userbookmanagementbackend.service.impl;



import lombok.AllArgsConstructor;
import org.example.userbookmanagementbackend.transformer.CategoryTransformer;
import org.example.userbookmanagementbackend.bean.Category;
import org.example.userbookmanagementbackend.dao.CategoryDao;
import org.example.userbookmanagementbackend.dto.CategoryDto;
import org.example.userbookmanagementbackend.exception.ResourceNotFoundException;
import org.example.userbookmanagementbackend.service.facade.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;
    private CategoryTransformer categoryTransformer;

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryDao.findAll();
        return categoryTransformer.toDto(categories);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category foundedCategory = categoryDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", id)
        );
        return categoryTransformer.toDto(foundedCategory);
    }

    @Override
    public int deleteById(Long id) {
        findById(id);
        categoryDao.deleteById(id);
        return 1;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryTransformer.toEntity(categoryDto);
        Category savedCategory = categoryDao.save(category);
        return categoryTransformer.toDto(savedCategory);
    }

    @Override
    public List<CategoryDto> save(List<CategoryDto> categoryDtos) {
        if (categoryDtos != null && !categoryDtos.isEmpty()) {
            return categoryDtos.stream().map(this::save).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        findById(categoryDto.id());
        Category category = categoryTransformer.toEntity(categoryDto);
        Category updatedCategory = categoryDao.save(category);
        return categoryTransformer.toDto(updatedCategory);
    }

    @Override
    public int delete(CategoryDto categoryDto) {
        CategoryDto foundedCategoryDto = findById(categoryDto.id());
        Category category = categoryTransformer.toEntity(foundedCategoryDto);
        categoryDao.delete(category);
        return 1;
    }

    @Override
    public void delete(List<CategoryDto> categoryDtos) {
        if (categoryDtos != null && !categoryDtos.isEmpty()) {
            categoryDtos.forEach(this::delete);
        }
    }

    @Override
    public void update(List<CategoryDto> categoryDtos) {
        if (categoryDtos != null && !categoryDtos.isEmpty()) {
            categoryDtos.forEach(this::update);
        }
    }
}
