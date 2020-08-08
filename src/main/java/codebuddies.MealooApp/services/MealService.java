package codebuddies.MealooApp.services;

import codebuddies.MealooApp.entities.Meal;
import codebuddies.MealooApp.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public boolean existsByName(String name) {
        return mealRepository.existsByName(name);
    }

    public Meal findByName(String name) {
        return mealRepository.findByName(name);
    }

    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal updateByName(String name, Meal meal) {
        Meal foundedMeal = findByName(name);
        if(meal.getPrice() != 0){
            foundedMeal.setPrice(meal.getPrice());
        }
        if(meal.getProducts() != null){
            foundedMeal.setProducts(meal.getProducts());
        }
        if(meal.getMealDifficulty()!=null){
            foundedMeal.setMealDifficulty(meal.getMealDifficulty());
        }
        save(foundedMeal);
        return foundedMeal;
    }

    public void deleteByName(String name) {
        mealRepository.deleteByName(name);
    }
}