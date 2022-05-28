package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Schueler implements Person {
    private static final List<Schueler> SCHUELER_LIST = new ArrayList<>();
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String klasse;
    @NonNull
    private String eMail;

    public Schueler(String firstName, String lastName, String klasse, String eMail) {
        this(null, firstName, lastName, klasse, eMail);
    }

    public static boolean addSchueler(Schueler schueler) {
        return SCHUELER_LIST.add(schueler);
    }

    public static boolean removeSchueler(Schueler schueler) {
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

    public Schueler() {
        this(1, "Max", "Muster", "4BHIF", "email");
    }
}
