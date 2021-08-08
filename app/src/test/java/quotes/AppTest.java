package quote;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void pathExists(){
        assertThrows(FileNotFoundException.class,() -> Quotes.randomQuote("recentquotes.json") );
    }

    @Test
    void containsQuote() throws Exception {
        String s = Quotes.randomQuote("src/test/resources/recentquotes.json");
        assertNotNull(s);
    }

    @Test
    void QuoteAPI() throws Exception {
        String s = Quotes.randomQuotesAPI("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");
        assertNotNull(s);
    }

    @Test
    void throwsExceptionConnecting(){
        assertThrows(Exception.class,() -> Quotes.randomQuotesAPI("blahblah") );
    }

    @Test
    void jsonToJsonFromStaysSame() throws FileNotFoundException {
        FileReader file = new FileReader("src/test/resources/recentquotes.json");
        Gson gson = new Gson();
        List<Quotes> quotes = Arrays.asList(gson.fromJson(file, Quotes[].class));
        String jsonString = gson.toJson(quotes);
        List<Quotes> quotesSecond = Arrays.asList(gson.fromJson(jsonString, Quotes[].class));

        assertEquals(quotes.toString(), quotesSecond.toString());
    }

}
