import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainClassTes extends MainClass {
    @Test
    public void testGetLocalNumber() {
        Assertions.assertTrue(this.getLocalNumber() == 14, "Вернулось число не 14");
    }
}
