package UI;

import org.junit.jupiter.api.Assertions;
import ru.ui.MyPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTest extends BaseTest {
    private MyPage m;

    @BeforeEach
    public void beforeTest() {
        m = new MyPage();
        open("https://demoqa.com/");

        assertAll(
                () -> assertEquals("sd", "sa"),
                () -> assertEquals("sd", "sa")
        );

    }

    @Test
    public void first() {
        Assertions.assertFalse(m.clickMe());
    }
}