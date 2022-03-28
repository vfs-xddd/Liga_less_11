import Enums.Options;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import interfaces.Checkbox;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class SwitcherM implements Checkbox {
    private static final String titleToControlXpath = "/ancestor::mvid-switcher //*[contains(@class, 'slider' )] ";
    public final String title;
    private final String titleXpath;
    private final String controlXpath;

    @Override
    public boolean isChecked() {
        SelenideElement el = $x(controlXpath);
        return !el.pseudo(":before", "transform").equals("none");
    }

    @Override
    public void on() {
        SelenideElement el = $x(controlXpath);
        el.scrollIntoView(false).shouldBe(visible);
        if (!isChecked()) el.click();
        $x("//mvid-plp-product-cards-layout").shouldBe(Condition.visible);

        el.shouldNotHave(Condition.pseudo(":before", "transform", "none"));


    }

    @Override
    public void off() {
        SelenideElement el = $x(controlXpath);
        el.scrollIntoView(false).shouldBe(visible);
        if (isChecked()) el.click();
        $x("//mvid-plp-product-cards-layout").shouldBe(Condition.visible);

        el.shouldHave(Condition.pseudo(":before", "transform", "none"));

    }

    @Override
    public int getCount() {
        System.out.println("Не реализовано здесь.");
        return 0;
    }

    SwitcherM (SelenideElement el){
        String text = el.getText();
        if (text.contains("\n")) this.title = el.getText().substring(0, text.indexOf("\n"));
        else this.title = el.getText();

        this.titleXpath = Options.FILTER_CONTAINER.xpath + "//*[contains(text(), '"+ title +"')]";
        this.controlXpath = titleXpath + titleToControlXpath;

    }
}
