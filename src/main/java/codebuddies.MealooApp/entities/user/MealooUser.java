package codebuddies.MealooApp.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class MealooUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(min = 5, max = 30)
    private String username;

    @NotBlank
    @Length(min = 6, max = 60)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "mealoo_user_roles",
                joinColumns = @JoinColumn(name = "mealoo_user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @NotBlank
    @Email
    @Column
    private String email;

    @Embedded
    private NutritionSettings nutritionSettings;

    @OneToMany(mappedBy = "mealooUser", orphanRemoval = true)
    @JsonIgnore
    private List<FoodDiary> foodDiaries;

    @Embedded
    private MealooUserDetails mealooUserDetails;

    public MealooUser() {
    }

    public MealooUser(String username, String password, String email,
                     NutritionSettings nutritionSettings,
                     MealooUserDetails mealooUserDetails) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nutritionSettings = nutritionSettings;
        this.mealooUserDetails = mealooUserDetails;
    }


    public MealooUser(Long id, String username, String password, String email,
                      NutritionSettings nutritionSettings,
                      MealooUserDetails mealooUserDetails) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nutritionSettings = nutritionSettings;
        this.mealooUserDetails = mealooUserDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NutritionSettings getNutritionSettings() {
        return nutritionSettings;
    }

    public void setNutritionSettings(NutritionSettings nutritionSettings) {
        this.nutritionSettings = nutritionSettings;
    }

    public List<FoodDiary> getFoodDiaries() {
        return foodDiaries;
    }

    public void setFoodDiaries(List<FoodDiary> foodDiaries) {
        this.foodDiaries = foodDiaries;
    }

    void addDiary(FoodDiary foodDiary) {
        foodDiaries.add(foodDiary);
    }

    public MealooUserDetails getMealooUserDetails() {
        return mealooUserDetails;
    }

    public void setMealooUserDetails(MealooUserDetails mealooUserDetails) {
        this.mealooUserDetails = mealooUserDetails;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealooUser that = (MealooUser) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(email, that.email) &&
                Objects.equals(nutritionSettings, that.nutritionSettings) &&
                Objects.equals(foodDiaries, that.foodDiaries) &&
                Objects.equals(mealooUserDetails, that.mealooUserDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles, email, nutritionSettings, foodDiaries, mealooUserDetails);
    }
}
