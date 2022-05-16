package domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.*;


@Getter
@Setter
public class BuchExemplar {
    private static final List<BuchExemplar> BUCH_EXEMPLAR_LIST = new ArrayList<>();

    private Integer id;
    @NonNull
    private BuchTyp typ;

    public BuchExemplar(BuchTyp typ) {
        this(null, typ);
    }

    public BuchExemplar(Integer id, BuchTyp typ) {
        this.id = id;
        this.setTyp(typ);
    }


    public static List<BuchExemplar> getBuchExemplarList() {
        return BUCH_EXEMPLAR_LIST;
    }

    public static boolean addBuchExemplar(BuchExemplar buchExemplar){
        return BUCH_EXEMPLAR_LIST.add(buchExemplar);
    }

    public static boolean removeBuchExemplar(BuchExemplar buchExemplar){
        return BUCH_EXEMPLAR_LIST.remove(buchExemplar);
    }

    @Override
    public String toString() {
        return String.format("Exemplar - ISBN:%s - Nr:#%d",getTyp().getIsbn(),id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuchExemplar exemplar = (BuchExemplar) o;
        return Objects.equals(id, exemplar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
