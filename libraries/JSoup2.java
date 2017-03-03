import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements;
import java.io.IOException;

public class JSoup2 {

    public static void main(String[] args) {
        
        try {
            String url = "http://feeds.feedburner.com/time/scienceandhealth";
            Document doc = Jsoup.connect(url).get();
            System.out.println(doc.title());
            // traverse selected elements
            Elements elements = doc.select("guid");
            for (Element element: elements) {
                System.out.println(element.ownText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
