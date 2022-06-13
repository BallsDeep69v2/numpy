package http;

import domain.BuchTyp;
import errorhandling.ISBNHttpException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpConnection {

    private static final String urlLink = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public static BuchTyp getBookInfosPerISBN(String isbn) {
        URL url;

        try {
            url = new URL(urlLink + isbn);
            String line;

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder httpResponse = new StringBuilder();
            while ((line = br.readLine()) != null) {
                httpResponse.append(line);
            }
            br.close();
            connection.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) parser.parse(httpResponse.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            assert jsonObject != null;
            JSONArray items = (JSONArray) jsonObject.get("items");
            JSONObject itemInfos = (JSONObject) items.get(0);
            JSONObject volumeInfo = (JSONObject) itemInfos.get("volumeInfo");

            String title = volumeInfo.getOrDefault("title", "").toString();
            String author = volumeInfo.getOrDefault("authors", "").toString();
            String description = volumeInfo.getOrDefault("description", "").toString();
            String genre = volumeInfo.getOrDefault("genre", "").toString();

            int year, pageCount;
            try {
                year = Integer.parseInt(volumeInfo.getOrDefault("publishedDate", "").toString());
                pageCount = Integer.parseInt(volumeInfo.getOrDefault("pageCount", "").toString());
            } catch (NumberFormatException ex) {
                year = 0;
                pageCount = 0;
            }
            return new BuchTyp(isbn, title, author, description, genre, year, pageCount);
        } catch (IOException e) {
            throw new ISBNHttpException();
        }
    }
}