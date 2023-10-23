package mealplanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MealDAOImplementation implements MealDAO{

    @Override
    public Meal get(int id) throws SQLException {
        Connection connection = Database.getConnection();


        String sql = "SELECT * FROM meals WHERE id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int meal_id = rs.getInt("meal_id");
            String category = rs.getString("category");
            String meal = rs.getString("meal");
            return new Meal(category, meal, meal_id);
        } else {
            System.out.printf("Meal with id %d not found\n", id);
            return null;
        }

    }

    @Override
    public ArrayList<Meal> getAll() throws SQLException {
        return null;
    }

    @Override
    public void add(Meal meal) throws SQLException {
        Connection connection = Database.getConnection();

        String sql = "INSERT INTO meals(category, meal, meal_id) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, meal.getCategory());
        ps.setString(2, meal.getName());
        ps.setInt(3, meal.getMeal_id());
        ps.executeUpdate();

    }

    @Override
    public void update(Meal meal) {
    }

    @Override
    public void deleteById(int id) {

    }

    public static int getMealId () {
        int id = 1;
        try {
            Connection connection = Database.getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("""
              SELECT meal_id
              FROM meals
              ORDER BY meal_id DESC
              LIMIT 1;""");
            while (resultSet.next()) {
                id = resultSet.getInt(1) + 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static ResultSet getMealsByCategory(String category, String orderBy) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement filterMealsByCategory = connection.prepareStatement("SELECT * FROM meals where category = ? ORDER BY meal_id ASC");
        PreparedStatement filterMealsByCategoryAlph = connection.prepareStatement("SELECT * FROM meals where category = ? ORDER BY meal ASC");
        filterMealsByCategory.setString(1, category);
        filterMealsByCategoryAlph.setString(1, category);
        if (orderBy.equals("meal_id")) {
            return filterMealsByCategory.executeQuery();
        } else {
            return filterMealsByCategoryAlph.executeQuery();
        }
    }


}
