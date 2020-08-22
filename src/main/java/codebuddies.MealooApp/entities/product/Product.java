package codebuddies.MealooApp.entities.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;



@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private double price;
    // todo add price in several currencies

    @NotNull
    private int caloriesPer100g;

    @Embedded
    private Macronutrients macronutrients;

    @NotNull
    private ProductType productType;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Ingredient> ingredients;

    public Product() {
    }

    public Product(String name, double price, int caloriesPer100g, Macronutrients macronutrients, ProductType productType) {
        this.name = name;
        this.price = price;
        this.macronutrients = macronutrients;
        this.caloriesPer100g = calculateCaloriesPer100g(this.caloriesPer100g);
        this.productType = productType;
    }

    public int calculateCaloriesPer100g(int caloriesPer100g){
        if(caloriesPer100g == 0) {
            return (macronutrients.getCarbohydratesPer100g() * 4) +
                    (macronutrients.getFatsPer100g() * 9) + (macronutrients.getProteinsPer100g() * 4);
        }
        return caloriesPer100g;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public void setCaloriesPer100g(int caloriesPer100g) {
        this.caloriesPer100g = caloriesPer100g;
    }

    public Macronutrients getMacronutrients() {
        return macronutrients;
    }

    public void setMacronutrients(Macronutrients macronutrients) {
        this.macronutrients = macronutrients;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                caloriesPer100g == product.caloriesPer100g &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(macronutrients, product.macronutrients) &&
                productType == product.productType &&
                Objects.equals(ingredients, product.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, caloriesPer100g, macronutrients, productType, ingredients);
    }

}
