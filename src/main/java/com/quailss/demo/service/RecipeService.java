package com.quailss.demo.service;

import com.quailss.demo.domain.Recipe;
import com.quailss.demo.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public Page<Recipe> getRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return recipeRepository.findAll(pageable);
    }

    public Page<Recipe> getRecipesByMenuIdAndKeyword(Long menuId, String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        if(menuId == 0)
            return recipeRepository.findAllByKeyword(keyword, pageable);
        else
            return recipeRepository.findAllByMenuIdAndKeyword(menuId, keyword, pageable);
    }

    public Optional<Recipe> getRecipe(Long recipeId){
        return recipeRepository.findById(recipeId);
    }

    public Page<Recipe> getRecipesByCategory(Long menuId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return recipeRepository.findAllByMenuId(menuId, pageable);
    }
}
