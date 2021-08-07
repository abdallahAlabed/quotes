package quote;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(Quotes.randomQuotesAPI("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en"));

    }

}
