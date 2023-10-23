package mealplanner;

import java.util.List;



public class Meal {
    private String category;
    private String name;
    private int meal_id;




    protected Meal(String category, String name, int meal_id) {
        this.category = category;
        this.name = name;
        this.meal_id = meal_id;

    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", meal_id=" + meal_id +
                '}';
    }
}

