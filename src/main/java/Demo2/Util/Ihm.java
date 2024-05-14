package Demo2.Util;

import Demo2.DAO.PlanteDAO;
import Demo2.Entity.Plante;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Ihm {
    private Connection connection;
    private PlanteDAO planteDAO;
    private Scanner scanner;

    public Ihm() {
        scanner = new Scanner(System.in);
        try {
            connection = DataBaseManager.getConnection();
            planteDAO = new PlanteDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        int entry;
        while (true) {
            System.out.println("Gestion de plante");
            System.out.println("1. Ajouter une plante");
            System.out.println("2. Liste des plantes");
            System.out.println("3. Modifier une plante");
            System.out.println("4. Supprimer une plante");
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    System.out.println("Ajouter une plante");
                    createPlante();
                    break;
                case 2:
                    System.out.println("Liste des plantes");
                    getAllPlante();
                    break;
                case 3:
                    System.out.println("Modifier une plante");
                    updatePlante();
                    break;
                case 4:
                    System.out.println("Supprimer une plante");
                    deletePlante();
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private void createPlante() throws RuntimeException {
        System.out.println("Nom de la plante: ");
        String name = scanner.nextLine();
        System.out.println("Age de la plante: ");
        int age;
        try {
            age = scanner.nextInt();
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Age must be a valid number.");
            return;
        }
        System.out.println("Couleur de la plante: ");
        String color = scanner.nextLine();

        try {
            planteDAO.createPlante(name, age, color);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de la plante");
        }
    }

    private void getAllPlante() {
        try {
            List<Plante> plantes = planteDAO.getAllPlante();
            for (Plante plante : plantes) {
                System.out.println(plante);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePlante() {
        System.out.println("ID de la plante à modifier: ");
        int id_plante = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nouveau nom de la plante: ");
        String nom = scanner.nextLine();
        System.out.println("Nouvel âge de la plante: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nouvelle couleur de la plante: ");
        String color = scanner.nextLine();

        try {
            if (planteDAO.updatePlante(id_plante, nom, age, color)) {
                System.out.println("Plante mise à jour avec succès.");
            } else {
                System.out.println("La plante avec l'ID spécifié n'existe pas.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de la plante: ");
        }
    }

    private void deletePlante() {
        System.out.println("ID de la plante à supprimer: ");
        int id_plante = scanner.nextInt();
        scanner.nextLine();
        try {
            if (planteDAO.deletePlante(id_plante)) {
                System.out.println("Plante supprimée avec succès.");
            } else {
                System.out.println("La plante n'existe pas.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de la plante: ");
        }
    }
}
