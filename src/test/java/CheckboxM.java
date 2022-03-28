import Enums.Options;
import Utils.WebUtils;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class CheckboxM implements interfaces.Checkbox {
    private static final String cssChecked = "checkbox__icon_checked";
    private static final String titleToIconXpath = "/ancestor::div[@class='checkbox']/mvid-icon";
    public final String title;
    private final String checkboxIconXpath;

    @Override
    public boolean isChecked() {
        return WebUtils.elCssClassContains($x(checkboxIconXpath),cssChecked);
    }

    @Override
    public void on() {
        if (!isChecked()) $x(checkboxIconXpath).click();
        Assertions.assertTrue(isChecked());
    }

    @Override
    public void off() {
        if (isChecked()) $x(checkboxIconXpath).click();
        Assertions.assertFalse(isChecked());
    }

    @Override
    public int getCount() {
        System.out.println("Не реализовано здесь.");
        return 0;
    }

    CheckboxM(SelenideElement el){
        this.title = el.$(new By.ByTagName("a")).getText();
        String titleXpath = Options.OPTIONS_CONTAINER.xpath + "//*[contains(text(), '" + title + "')]";
        this.checkboxIconXpath = titleXpath + titleToIconXpath;
    }
}
