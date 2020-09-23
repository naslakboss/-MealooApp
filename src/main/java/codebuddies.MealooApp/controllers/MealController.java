package codebuddies.MealooApp.controllers;

import codebuddies.MealooApp.dto.MealDTO;
import codebuddies.MealooApp.entities.meal.Meal;
import codebuddies.MealooApp.services.MealService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/meals")
public class MealController {

    private MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("")
    public ResponseEntity<Page<MealDTO>> getAllMeals(Pageable pageable){
        return ResponseEntity.ok(mealService.getAllMeals(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<MealDTO> getMeal(@PathVariable String name) {
        return ResponseEntity.ok(mealService.getMealByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<MealDTO> createMeal(@Valid @RequestBody Meal meal){
        return ResponseEntity.ok(mealService.createMeal(meal));
    }

    @PostMapping("{name}/image")
    public ResponseEntity<MealDTO> addImage(@PathVariable String name, @RequestParam("filePath") String filePath) throws IOException {
        return ResponseEntity.ok(mealService.addImageToMeal(name, filePath));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    @DeleteMapping("/{name}/image")
    public ResponseEntity deleteImage(@PathVariable String name, @RequestParam("fileUrl") String fileUrl) throws IOException {
        mealService.deleteImageFromMeal(name, fileUrl);
        return ResponseEntity.ok("Image was successfully deleted from the meal");
    }

    @PutMapping("/{name}")
    public ResponseEntity<MealDTO> updateMeal(@Valid @RequestBody MealDTO mealDTO, @PathVariable String name) {
        return ResponseEntity.ok(mealService.updateMealByName(mealDTO, name));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    @DeleteMapping("/{name}")
    public ResponseEntity deleteMeal(@PathVariable String name) {
        mealService.deleteMealByName(name);
        return ResponseEntity.ok("Meal " + name + " was successfully deleted from Repository");
    }
}
