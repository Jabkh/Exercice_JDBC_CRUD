package Demo2.DAO;

import Demo2.Entity.Plante;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanteDAO {


    private Connection connection;

    private PreparedStatement preparedStatement;

    private String request;

    private ResultSet resultSet;

    public PlanteDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createPlante(String nom, int age, String color) {
        try {
            request = "INSERT INTO plante (nom, age, color) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, color);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Ajout réussi");
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Plante> getAllPlante () throws SQLException {
        List<Plante> plantes = new ArrayList<>();
        request = "SELECT * FROM plante";
        preparedStatement = connection.prepareStatement(request);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            plantes.add(Plante.builder().id_plante(resultSet.getInt("id_plante"))
                    .nom(resultSet.getString("nom"))
                    .color(resultSet.getString("color"))
                    .age(resultSet.getInt("age"))
                    .build());
        }
        return plantes;
    }

    public boolean updatePlante(int id, String nom, int age, String color) {
        try {
            String request = "UPDATE plante SET nom=?, age=?, color=? WHERE id_plante=?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, color);
            preparedStatement.setInt(4, id);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error pendant l'update", e);
        }
    }

    public boolean deletePlante(int id) {
        try {
            String request = "DELETE FROM plante WHERE id_plante=?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error pendant la suppression", e);
        }
    }

}
