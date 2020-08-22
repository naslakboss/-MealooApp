package codebuddies.MealooApp.dataProviders;

import codebuddies.MealooApp.entities.meal.MealDifficulty;
import codebuddies.MealooApp.entities.product.Macronutrients;

import java.util.List;

public class MealDTO {

    String name;

    List<IngredientForMealDTO> ingredients;

    double price;

    MealDifficulty mealDifficulty;

    Macronutrients macronutrients;

    int totalCalories;

    public MealDTO() {
    }

    public MealDTO(String name, List<IngredientForMealDTO> ingredients, double price,
                   MealDifficulty mealDifficulty, Macronutrients macronutrients, int totalCalories) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.mealDifficulty = mealDifficulty;
        this.macronutrients = macronutrients;
        this.totalCalories = totalCalories;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientForMealDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientForMealDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MealDifficulty getMealDifficulty() {
        return mealDifficulty;
    }

    public void setMealDifficulty(MealDifficulty mealDifficulty) {
        this.mealDifficulty = mealDifficulty;
    }

    public Macronutrients getMacronutrients() {
        return macronutrients;
    }

    public void setMacronutrients(Macronutrients macronutrients) {
        this.macronutrients = macronutrients;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }
}