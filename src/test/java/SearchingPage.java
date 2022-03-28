import Enums.Card;
import Enums.Options;
import com.codeborne.selenide.*;
import interfaces.*;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static Utils.WebUtils.elCssClassContains;
import static com.codeborne.selenide.Selenide.*;

public class SearchingPage {
    public static final String paginationXpath = "//mvid-pagination";

    /**Все фильтры отсюда*/
    public static Filter filter() {
        return  new Filter() {

            @Override
            public List<Category> getCategories() {
                return Options.CATEGORIES.findAll().stream().map(CategoryM::new).collect(Collectors.toList());
            }

            @Override
            public List<Checkbox> getSwitchers() {
                return Options.SWITCHER.findAll().stream().map(SwitcherM::new).collect(Collectors.toList());
            }
        };

    }

    /**Пагиннация*/
    public static Slider pages() {

        return new Slider() {

            private List<SelenideElement> getPagesList() { return $$x(paginationXpath + "//li");}

            @Override
            public void next() {
                List<SelenideElement> pagesList = getPagesList();
                SelenideElement el = pagesList.get(pagesList.size()-1);
                if (elCssClassContains(el, "disabled")) return;
                el.click();
            }

            @Override
            public void previous() {
                SelenideElement el = getPagesList().get(0);
                if (elCssClassContains(el, "disabled")) return;
                el.click();

            }

            @Override
            public boolean goForwardTo(int num){
                int lastnum = Integer.parseInt(getPagesList().get(getPagesList().size()-2).getText());  //последний элемент стрелка вперед, берем предпоследний
                //TODO можно включить поиск и назад
                while (current()!=num) {
                    if (current()==lastnum) return false;
                    next();
                }
                return true;

            }

            @Override
            public void last() {
                List<SelenideElement> list = getPagesList();
                list.get(list.size()-2).click();
            }

            @Override
            public int size() {
                List<SelenideElement> list = getPagesList();
                return Integer.parseInt(list.get(list.size()-2).getText());
            }

            @Override
            public int current(){
               return Integer.parseInt(getPagesList().stream()
                        .filter(el-> elCssClassContains(el, "active"))
                        .collect(Collectors.toList())
                        .get(0)
                        .find(new By.ByTagName("span"))
                        .getText());
            }


        };
    }

    /**Ищет товар по тексту, при частичном совпадении остановится на 1ом*/
    public static void findProduct(String productTitle) {

        while (true) {
            $x("//mvid-plp-product-cards-layout").shouldBe(Condition.visible);

            int time = 1000;
            WaitStableResult scrolling = new WaitStableResult() {};
            scrolling.waitResultBecomeStableWhileDoingSmth(
                    ()-> Card.TITLE.findAll().size(),
                    ()-> {
                        int num = Card.TITLE.findAll().size()-1;
                        Card.TITLE.findAll().get(num).scrollIntoView(false).shouldBe(Condition.visible);},
                    time);


            ElementsCollection resultElem = Card.TITLE.find(productTitle);
            if (!resultElem.isEmpty()) {
                resultElem.get(0).scrollIntoView(false).hover();
                break;
            }
            if (pages().current() == pages().size()) {
                System.out.println("Подходящих элементов не найдено!");
                break;
            }
            pages().next();
        }

    }


}
