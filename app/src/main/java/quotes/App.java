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
        String apiURL = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";
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
                while (line != null) {
                    System.out.println(line);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                Gson gson = new Gson();
                Quotes[] s = gson.fromJson(line, Quotes[].class);
                System.out.println(Arrays.toString(s));
            } else {
                System.out.println("An error occurred with status " + status);
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
