import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSoup {

    public static void main(String[] args) {
        String html = "<html><head><title>Title</title></head>"
		      + "<body><h1>Body Content</h1>"
		      + "</body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.title());
        System.out.println(doc.head());
        System.out.println(doc.body());
    }
}
