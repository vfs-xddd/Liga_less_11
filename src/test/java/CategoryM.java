import Enums.Options;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import interfaces.Checkbox;
import interfaces.Price;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;

public class CategoryM implements interfaces.Category, Price {
    private static final String titleXpathAdd = "./*[contains(@class,  'title'  )]";
    private static final String titleToContainerXpath = "/ancestor::div[contains(@class, 'accordion__container')]";
    public static final String containerToSearchXpath = "//*[contains(@class,'input__container')]/input";
    public static final String minPriceXpath = "//input[@name= 'minPrice' ]";
    public static final String maxPriceXpath = "//input[@name= 'maxPrice' ]";
    public final String title;
    private final String titleXpath;
    private final String containerXpath;

    @Override
    public boolean isExpand() {
      return $x(containerXpath).$(new By.ByClassName(Options.OPTIONS_CONTAINER.cssClass)).isDisplayed();
    }

    @Override
    public void collapse() {
        if (!isExpand()) return;
        $x(titleXpath).click();
    }

    @Override
    public void expand() {
        if (isExpand()) return;
        $x(titleXpath).click();
    }

    @Override
    public List<Checkbox> getCheckboxList() {
            return Options.CHECKBOX.findIn($x(containerXpath))
                    .stream()
                    .map(CheckboxM::new)
                    .collect(Collectors.toList());
    }

    @Override
    public SelenideElement searchField() {
        return $x(containerXpath+containerToSearchXpath).scrollIntoView(false).shouldBe(Condition.exist);
        //  check //*[contains(@class, 'active-search'    )]
    }

    @Override
    public void showAll() {
        System.out.println("Не реализовано здесь.");
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public SelenideElement minPriceField() {
        return $x(minPriceXpath).scrollIntoView(false).shouldBe(Condition.visible);
    }

    @Override
    public SelenideElement maxPriceField() {
        return $x(maxPriceXpath).scrollIntoView(false).shouldBe(Condition.visible);
    }

    CategoryM(SelenideElement el){
        this.title = el.$x(titleXpathAdd).getText();
        this.titleXpath = Options.FILTER_CONTAINER.xpath + "//*[contains(text(), '"+ title +"')]";
        this.containerXpath = titleXpath + titleToContainerXpath;
    }
}
