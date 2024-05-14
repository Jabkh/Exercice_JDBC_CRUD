package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/demo_jdbc";

        String user = "root";

        String password = "Mysql";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database");


//                String request = "INSERT INTO personne (prenom,nom) VALUES ('John', 'Doe')";
//                Statement statement = connection.createStatement();
//                boolean result = statement.execute(request);
//                System.out.println("Result: " + result);
//                if (!result) {
//                    System.out.println("Aucun résultat trouvé");
//                }

                String request = "INSERT INTO personne (prenom,nom) VALUES (?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, "prenom");
                preparedStatement.setString(2, "nom");

                int row = preparedStatement.executeUpdate();
                ResultSet resultSetKey = preparedStatement.getGeneratedKeys();

                if (row > 0) {
                    System.out.println("Insertion réussie");
                }
                if (resultSetKey.next()) {
                    System.out.println("Clé générée: " + resultSetKey.getInt(1));
                } else {
                    System.out.println("Insertion échouée");
                }
                String request1 = "SELECT * FROM personne";
                Statement statement1 = connection.createStatement();
                ResultSet result1 = statement1.executeQuery(request1);
                while (result1.next()) {
                    System.out.println("prenom: " + result1.getString("prenom") + " nom: " + result1.getString("nom"));
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}