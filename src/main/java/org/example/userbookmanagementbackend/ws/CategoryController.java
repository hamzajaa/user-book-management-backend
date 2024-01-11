package org.example.userbookmanagementbackend.ws;

import org.example.userbookmanagementbackend.dto.CategoryDto;
import org.example.userbookmanagementbackend.service.facade.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.save(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("/list/")
    public ResponseEntity<List<CategoryDto>> save(@RequestBody List<CategoryDto> categoryDtos) {
        return new ResponseEntity<>(categoryService.save(categoryDtos), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }

    @DeleteMapping("/")
    public ResponseEntity<Integer> delete(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.delete(categoryDto));
    }

    @DeleteMapping("/list/")
    public void delete(@RequestBody List<CategoryDto> categoryDtos) {
        categoryService.delete(categoryDtos);
    }

    @PutMapping("/list/")
    public void update(@RequestBody List<CategoryDto> list) {
        categoryService.update(list);
    }
}
