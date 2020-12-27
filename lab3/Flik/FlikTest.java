import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {

    @Test
    public void testisSameNumber() {
        int a = 128;
        int b = 128;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
