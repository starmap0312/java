import junit.framework.Test;
import junit.framework.TestCase;

public class UnitTest extends TestCase {
    public void testApp() {
        // fail("this will throw AssertionFailedError");
        assertTrue(true);
        assertEquals("check two strings", "abc", "abc"); 
    }

    public static void main(String[] args) {
        UnitTest test = new UnitTest();
        test.testApp();
    }    
}
