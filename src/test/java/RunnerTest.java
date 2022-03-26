import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;


public class RunnerTest {
    public static final String href = "https://www.mvideo.ru/holodilniki-i-morozilniki-2687/holodilniki-i-morozilnye-kamery-159";
    public static final String logo_xpath = "//*[contains(@class, 'logo')]";

    @BeforeAll
    public static void before() {
        WebDriverRunner.setWebDriver(WebDriverHandler.getDriver());
        Selenide.open(href);
        $x(logo_xpath).shouldBe(Condition.visible);
    }

    @Test
    public void testPageMethods() {

        //Card.TITLE.inCard("Холодильник Haier C2F636CCFD").shouldBe(Condition.visible).click();

        //SelenideElement elem = $x("//*[contains(@class,'product-picture-overlay')]");
        //Slider slider = Card.SLIDER.slider(elem);

        Card.TITLE.findAll().get(0).click();





    }
}
