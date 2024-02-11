package com.example.EcommerceJWT.Services;

import com.example.EcommerceJWT.Model.Category;
import com.example.EcommerceJWT.Model.Product;
import com.example.EcommerceJWT.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deletebyid(Integer userId) {
        categoryRepository.deleteById(userId);
    }

    public List<Category> getallcategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findcategorybyid(int id){
        return categoryRepository.findById(id);
    }
    public Category updateCategory(Category category, int id){
        Category category1= findcategorybyid(id).get();
        if(category.getName()!=null)category1.setName(category.getName());
        if(category.getDescription()!=null)category1.setDescription(category.getDescription());
        return categoryRepository.save(category1);
    }

    public List<Category> getCategoryByField(Category category){

        if(category.getDescription()!=null && category.getName()!=null)
            return categoryRepository.findByNameAndDescription(category.getName(), category.getDescription());
        else if(category.getName()!=null) {
            return categoryRepository.findByName(category.getName());
        }
        else if(category.getDescription()!=null)
            return categoryRepository.findByDescription(category.getDescription());


        return null;
    }
}
