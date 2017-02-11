// jsoup: Java HTML Parser
// 1) it provides a very convenient API for extracting and manipulating data, using DOM, CSS, and jquery-like methods
// 2) it parses HTML to the same DOM as modern browsers do
//    a) scrape and parse HTML from a URL, file, or string
//    b) find and extract data, using DOM traversal or CSS selectors
//    c) manipulate the HTML elements, attributes, and text
//    d) clean user-submitted content against a safe white-list, to prevent XSS attacks
//    e) output tidy HTML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class JSoup {

    public static void main(String[] args) {
        // 1) parse HTML from a string
        String html = "<html>"
                    + "<head><title>Title</title></head>"
		    + "<body><h1>Body Content</h1></body>"
                    + "</html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.title());
        System.out.println(doc.head());
        System.out.println(doc.body());
        // 2) parse HTML from a URL 
        try {
            Document doc2 = Jsoup.connect("http://en.wikipedia.org/").get();
            System.out.println(doc2.title());
            Elements newsHeadlines = doc2.select("#mp-itn b a");
            System.out.println(newsHeadlines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
