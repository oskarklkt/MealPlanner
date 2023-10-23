package mealplanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingList {

    public static ArrayList<Integer> getMealsList() throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        ArrayList<Integer> mealsIds = new ArrayList<>();

        String sql = "SELECT meal_id from meals join plan on plan.breakfast = meals.meal\n" +
                "UNION ALL\n" +
                "SELECT meal_id from meals join plan on plan.lunch = meals.meal \n" +
                "UNION ALL\n" +
                "SELECT meal_id from meals join plan on plan.dinner = meals.meal";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            mealsIds.add(rs.getInt(1));
        }

        return mealsIds;
    }

    public static ArrayList<String> getIngredientsForPlan() throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        ArrayList<Integer> mealsIds = getMealsList();
        ArrayList<String> ingredients = new ArrayList<>();

        String sql = "SELECT ingredient FROM ingredients WHERE meal_id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        for (int mealId : mealsIds) {
            ps.setInt(1, mealId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String ingredient = rs.getString(1);
                ingredients.add(ingredient);
            }
        }


        return ingredients;
    }

    public static void getShoppingList() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> ingredients = getIngredientsForPlan();
        if (ingredients.isEmpty()) {
            System.out.println("Unable to save. Plan your meals first.");
        } else {
            System.out.println("Input a filename:");
            String path = scanner.nextLine();
            Map<String, Integer> nOfIngredients = new HashMap<>();
            for (String ingredient : ingredients) {
                if (!nOfIngredients.containsKey(ingredient)) {
                    nOfIngredients.put(ingredient, 1);
                } else {
                    nOfIngredients.put(ingredient, nOfIngredients.get(ingredient) + 1);
                }
            }
            try {
                File file = new File(path);
                FileWriter writer = new FileWriter(file);
                for (String ingredient : nOfIngredients.keySet()) {
                    if (nOfIngredients.get(ingredient) > 1) {
                        writer.write(ingredient + " " + "x" + nOfIngredients.get(ingredient) + "\n");
                    } else {
                        writer.write(ingredient + "\n");
                    }
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Saved!");
        }




    }
}
