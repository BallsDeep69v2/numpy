package domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class BuchExemplar {
    private static final List<BuchExemplar> BUCH_EXEMPLAR_LIST = new ArrayList<>();

    @NonNull
    private Integer id;

    @NonNull
    private BuchTyp typ;

    public BuchExemplar(@NonNull Integer id, @NonNull BuchTyp typ) {
        this.id = id;
        setTyp(typ);
    }

    public static List<BuchExemplar> getBuchExemplarList() {
        return BUCH_EXEMPLAR_LIST;
    }

    public static boolean addBuchExemplar(BuchExemplar buchExemplar) {
        return BUCH_EXEMPLAR_LIST.add(buchExemplar);
    }

    public static boolean removeBuchExemplar(BuchExemplar buchExemplar) {
        return BUCH_EXEMPLAR_LIST.remove(buchExemplar);
    }

    @Override
    public String toString() {
        return String.format("Exemplar - ISBN:%s - Exemplar-Nr:#%d", getTyp().getIsbn(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuchExemplar that = (BuchExemplar) o;
        return id.equals(that.id) && typ.getIsbn().equals(that.typ.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typ.getIsbn());
    }
}
