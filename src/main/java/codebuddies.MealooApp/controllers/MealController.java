package codebuddies.MealooApp.controllers;

import codebuddies.MealooApp.entities.*;
import codebuddies.MealooApp.exceptions.EntityAlreadyFoundException;
import codebuddies.MealooApp.services.MealService;
import codebuddies.MealooApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    MealService mealService;

    @Autowired
    ProductService productService;

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB(){
//
//        Product egg = new Product("Eggs", 1, 140,
//                new Macronutrients(13, 1, 10));
//        Product bread = new Product("Bread",3, 264,
//                new Macronutrients(9, 50, 3));
//        Set<Product> list = new HashSet<>();
//        productService.save(egg);
//        productService.save(bread);
//        list.add(egg);
//        list.add(bread);
//        Meal meal = new Meal("Scrambled-eggs", list, 5, "EASY");
//        mealService.save(meal);
//    }

    @GetMapping("")
    public ResponseEntity<List<Meal>> findAllMeals(){
        return ResponseEntity.ok(mealService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Meal> findMealByName(@PathVariable String name){
        Meal searchedMeal = mealService.findByName(name);
        return searchedMeal != null ? ResponseEntity.ok(searchedMeal) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Meal> addMeal(@RequestBody @Valid Meal meal){
        if(mealService.existsByName(meal.getName())) throw new EntityAlreadyFoundException("Meal");
        mealService.save(meal);
        return ResponseEntity.created(URI.create("/" + meal.getName())).body(meal);
    }

    @PatchMapping("/patch/{name}")
    public ResponseEntity<Meal> patchMealByName(@PathVariable String name, @Valid @RequestBody Meal meal){
        Meal patchedMeal = mealService.findByName(name);
        return patchedMeal != null ? ResponseEntity.ok(mealService.updateByName(name, meal)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteByName(@PathVariable String name){
        if(!mealService.existsByName(name)){
            return ResponseEntity.notFound().build();
        }
        mealService.deleteByName(name);
        return ResponseEntity.ok("Meal " + name + " was successfully deleted from Repository");
    }
}
