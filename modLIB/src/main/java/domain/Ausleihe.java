package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class Ausleihe {
    private static final List<Ausleihe> AUSLEIHE_LIST = new ArrayList<>();
    private BuchExemplar exemplar;
    private Person ausleiher;
    private LocalDate beginDate;
    private boolean zurueckgegeben;

    public static boolean addAusleihe(Ausleihe ausleihe){
        return AUSLEIHE_LIST.add(ausleihe);
    }

    public static boolean removeAusleihe(Ausleihe ausleihe){
        return AUSLEIHE_LIST.remove(ausleihe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ausleihe ausleihe = (Ausleihe) o;
        return Objects.equals(exemplar, ausleihe.exemplar) && Objects.equals(ausleiher, ausleihe.ausleiher) && Objects.equals(beginDate, ausleihe.beginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exemplar, ausleiher, beginDate);
    }
}
