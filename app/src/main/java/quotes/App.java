package quote;

import com.google.gson.Gson;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println(Quotes.randomQuote("recentquotes.json"));
        System.out.println("------------------");


    }
}