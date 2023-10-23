package mealplanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAOImplementation implements IngredientDAO{
    @Override
    public Ingredient get(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Ingredient> getAll() throws SQLException {
        return null;
    }

    @Override
    public void add(Ingredient ingredient) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ingredients(ingredient, ingredient_id, meal_id)\n" +
                    "VALUES(?, ?, ?);");
            preparedStatement.setString(1, ingredient.ingredient);
            preparedStatement.setInt(2, ingredient.ingredient_id);
            preparedStatement.setInt(3, ingredient.meal_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Ingredient ingredient) {

    }

    @Override
    public void deleteById(int id) {

    }

    public static int getIngredientId () {
        int id = 1;
        try {
            Connection connection = Database.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("""
              SELECT ingredient_id
              FROM ingredients
              ORDER BY ingredient_id DESC
              LIMIT 1;""");
            while (resultSet.next()) {
                id = resultSet.getInt(1) + 1;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static ResultSet getIngredientsByMealId(int id) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ingredients WHERE meal_id = ? ORDER BY ingredient_id ASC");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}
