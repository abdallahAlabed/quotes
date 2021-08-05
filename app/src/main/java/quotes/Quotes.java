package quote;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Quotes {
    private String author;
    private String text;

    public Quotes(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Quotes(String author, String text, String patata) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Quote:\n" +
                "author='" + author + "\'\n" +
                "quote=" + text + '\"';
    }

    public static String randomQuote(String place) throws FileNotFoundException {
        FileReader file = new FileReader(place);
        Gson gson = new Gson();
        List<Quotes> quotes = Arrays.asList(gson.fromJson(file, Quotes[].class));
        int randomNumber = (int)(Math.random()*quotes.size());
        return quotes.get(randomNumber).toString();
    }

}