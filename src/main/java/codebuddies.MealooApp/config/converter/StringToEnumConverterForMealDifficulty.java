package codebuddies.MealooApp.config.converter;

import codebuddies.MealooApp.entities.meal.MealDifficulty;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverterForMealDifficulty implements Converter<String, MealDifficulty> {

    @Override
    public MealDifficulty convert(String source) {
        return MealDifficulty.valueOf(source.toUpperCase());
    }
}