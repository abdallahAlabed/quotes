package quote;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quotes {
    private String author;
    private String text;
    private String quoteText;
    private String quoteAuthor;

    public Quotes(String author, String text) {
        this.author = author;
        this.text = text;
        this.quoteAuthor = author;
        this.quoteText = text;
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

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    @Override
    public String toString() {
        return "Quote:\n" +
                "author='" + author + "\'\n" +
                "quote=" + text + '\"';
    }

    public static String randomQuote(String place) throws Exception {
        FileReader file = new FileReader(place);
        Gson gson = new Gson();
        List<Quotes> quotes = Arrays.asList(gson.fromJson(file, Quotes[].class));
        int randomNumber = (int) (Math.random() * quotes.size());
        return quotes.get(randomNumber).toString();
    }

    public static String randomQuotesAPI(String URL) throws Exception {
        String apiURL = URL;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            int status = connection.getResponseCode();

            if (status == 200) {
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                bufferedReader.close();

                Gson gson = new Gson();

                connection.disconnect();

                Quotes s = gson.fromJson(line, Quotes.class);
                String result = "From API:\nAuthor: " + s.getQuoteAuthor() + "\nQuote: " + s.getQuoteText();

                FileReader file = new FileReader("recentquotes.json");
//                List<Quotes> quotes = Arrays.asList(gson.fromJson(file, Quotes[].class));
                Quotes newQuote = new Quotes(s.getQuoteAuthor(), s.getQuoteText());

                Type userListType = new TypeToken<ArrayList<Quotes>>(){}.getType();
                ArrayList<Quotes> userArray = gson.fromJson(file, userListType);

                if (!userArray.contains(s)) {
                    userArray.add(newQuote);
                }

                try {
                    FileWriter fstream = new FileWriter("recentquotes.json");
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(gson.toJson(userArray));
                    out.close();
                } catch (Exception e) {
                    System.err.println("Error while writing to file: " +
                            e.getMessage());
                }

                return result;

            } else {
                connection.disconnect();
                System.out.println("else");
                System.out.println(Quotes.randomQuote("recentquotes.json"));
                return Quotes.randomQuote("recentquotes.json");
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(Quotes.randomQuote("recentquotes.json"));
            return Quotes.randomQuote("recentquotes.json");
        }

    }

}
