package mealplanner;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    Connection connection = Database.getConnection();
    Controller.start();

  }
}

