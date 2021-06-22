import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        Assertions.assertTrue(this.getLocalNumber() == 14, "Вернулось число не 14");
    }

    @Test
    public void testGetClassNumber() {
        Assertions.assertTrue(getClassNumber() > 45, "Вернулось число " + getClassNumber() + ", а это меньше 45");
    }
}