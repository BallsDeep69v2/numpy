package domain;

import javafx.util.converter.BigIntegerStringConverter;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BuchTyp {
    private static final List<BuchTyp> BUCH_TYP_LIST = new ArrayList<>();

    @NonNull
    private String isbn;

    @NonNull
    private String title;

    @NonNull
    private String author;

    private String description;

    private String genre;

    private Integer year;

    private Integer numberOfPages;

    public BuchTyp(@NonNull String isbn, @NonNull String title, @NonNull String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public static boolean addBuchTyp(BuchTyp typ) {
        return BUCH_TYP_LIST.add(typ);
    }

    public static boolean removeBuchTyp(BuchTyp typ) {
        return BUCH_TYP_LIST.remove(typ);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuchTyp buchTyp = (BuchTyp) o;
        return Objects.equals(isbn, buchTyp.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
