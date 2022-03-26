import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class SearchingPage {
    private static final String h1 = "System Dashboard";
    private final static String main_page_url = "https://edujira.ifellow.ru/secure/Dashboard.jspa";


    @FindBy(how = How.XPATH, using = "//div[@id='dashboard-content']//h1[text()='" + h1 +"']")
    private static SelenideElement main_h1;


}
