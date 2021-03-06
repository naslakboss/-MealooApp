package codebuddies.MealooApp.controllers;

import codebuddies.MealooApp.dto.MealDTO;
import codebuddies.MealooApp.services.MealService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("")
    public ResponseEntity<Page<MealDTO>> getAllMeals(Pageable pageable) {
        return ResponseEntity.ok(mealService.getAllMeals(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<MealDTO> getMeal(@PathVariable String name) {
        return ResponseEntity.ok(mealService.getMealByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<MealDTO> createMeal(@Valid @RequestBody MealDTO meal) {
        return ResponseEntity.ok(mealService.createMeal(meal));
    }

    @PostMapping("{name}/image")
    public ResponseEntity<MealDTO> addImage(@PathVariable String name, @RequestParam("filePath") String filePath) {
        return ResponseEntity.ok(mealService.addImageToMeal(name, filePath));
    }

    @DeleteMapping("/{name}/image")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteImage(@PathVariable String name, @RequestParam("fileUrl") String fileUrl) {
        mealService.deleteImageFromMeal(name, fileUrl);
        return ResponseEntity.ok("Image was successfully deleted from the meal");
    }

    @PutMapping("/{name}")
    public ResponseEntity<MealDTO> updateMeal(@Valid @RequestBody MealDTO meal, @PathVariable String name) {
        return ResponseEntity.ok(mealService.updateMealByName(meal, name));
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteMeal(@PathVariable String name) {
        mealService.deleteMealByName(name);
        return ResponseEntity.ok("Meal " + name + " was successfully deleted from Repository");
    }
}
