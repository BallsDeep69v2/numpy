package domain;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Ausleihe {

    @NonNull
    private BuchExemplar exemplar;

    @NonNull
    private BuchTyp buchtyp;

    @NonNull
    private Person ausleiher;

    @NonNull
    private LocalDate beginDate;

    /**
     * false = wenn noch nicht zurückgegeben
     * true = wenn zurückgegeben
     */
    private boolean status;

    public Ausleihe(@NonNull BuchExemplar exemplar, @NonNull BuchTyp buchtyp, @NonNull Person ausleiher, @NonNull LocalDate beginDate) {
        this.exemplar = exemplar;
        this.buchtyp = buchtyp;
        this.ausleiher = ausleiher;
        this.beginDate = beginDate;
        this.status = false;
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
