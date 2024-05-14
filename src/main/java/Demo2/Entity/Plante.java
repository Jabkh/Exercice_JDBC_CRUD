package Demo2.Entity;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Plante {
    private int id_plante;
    private String nom;
    private int age;
    private String color;

    @Override
    public String toString() {
        return "Plante{" +
                "id_plante=" + id_plante +
                ", nom='" + nom + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
