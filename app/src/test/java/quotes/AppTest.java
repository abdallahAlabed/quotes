package quote;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void pathExists(){
        assertThrows(FileNotFoundException.class,() -> Quotes.randomQuote("recentquotes.json") );
    }

    @Test
    void containsQuote() throws FileNotFoundException {
        String s = Quotes.randomQuote("src/test/resources/recentquotes.json");
        assertNotNull(s);
    }

}