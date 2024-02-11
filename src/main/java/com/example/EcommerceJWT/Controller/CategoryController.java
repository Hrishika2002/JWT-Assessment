package com.example.EcommerceJWT.Controller;

import com.example.EcommerceJWT.Model.Category;
import com.example.EcommerceJWT.Model.Product;
import com.example.EcommerceJWT.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/category/add-category")
    @ResponseStatus(HttpStatus.OK)
    public Category save(@RequestBody Category category){
        System.out.println("Check");
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/category/{id}")
    public void deletebyid(@PathVariable Integer id)  {
        categoryService.deletebyid(id);
    }

    @GetMapping("/categories")
    public List<Category> getallcategory() {
        return categoryService.getallcategory();
    }

    @PostMapping("/updateCategory/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(category,id);
        return updatedCategory;
    }

    @GetMapping("/getCategoryByField")
    public List<Category> getCategoryByField(@RequestBody(required = false) Category category){
        System.out.println(category);
        if(category==null){
            return categoryService.getallcategory();
        }
        return categoryService.getCategoryByField(category);

    }

}
