package decode;

import domain.BuchTyp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

public class Decode {
    public Optional<BuchTyp> getBuchTypFromISBN(String isbn) {
        try {
            URL urlToBookData = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn);
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
        throw new UnsupportedOperationException();
    }
}
