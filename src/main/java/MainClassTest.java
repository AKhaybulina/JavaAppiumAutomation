import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        Assertions.assertTrue(getLocalNumber() == 14, "Вернулось число " + getLocalNumber() + ", а не 14");
    }
}
