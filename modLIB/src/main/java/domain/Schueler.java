package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Schueler implements Person{
    private static final List<Schueler> SCHUELER_LIST = new ArrayList<>();
    private Integer id;
    private String firstName;
    private String lastName;
    private String klasse;
    private String eMail;

    public Schueler(String firstName, String lastName, String klasse, String eMail) {
        this(null, firstName, lastName, klasse, eMail);
    }

    public static boolean addSchueler(Schueler schueler) {
        return SCHUELER_LIST.add(schueler);
    }

    public static boolean removeSchueler(Schueler schueler){
        return SCHUELER_LIST.remove(schueler);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schueler schueler = (Schueler) o;
        return Objects.equals(id, schueler.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
