package Enums;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import interfaces.EnumWeb;
import interfaces.Slider;
import org.openqa.selenium.By;

import java.util.Collection;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public enum Card implements EnumWeb {
    TITLE       ("product-title__text", ""),
    LABEL       ("product-label__item", "//span[@class='label']"),
    PRICE_MAIN  ("price__main-value", ""),
    PRICE_SALE  ("price__sale-value", ""),
    PICTURE     ("product-picture__img", ""),
    SLIDER      ("product-picture-overlay", ""),
    MBONUS      ("mbonus-block__value",""),
    ADD_BUTTON  ("product-checkout__button", ""),
    LIKES       ("wishlist-button-block", ""),
    COMPARES    ("compare-button-block", ""),
    RAITING     ("stars-container", "//span[contains(@class,'value')]"),
    FEEDBACKS   ("product-rating__feedback",""),
    ;


    public final String cssClass;
    public final String xpath;

    Card(String cssClass, String xpathAdd) {
        this.cssClass = cssClass;
        this.xpath = "//*[contains(@class,'" + cssClass + "')]"+xpathAdd;
    }

    /**@param el контейнер слайдера с (@class,'product-picture-overlay')"*/
    public Slider slider(SelenideElement el){
        el.shouldHave(Condition.cssClass(SLIDER.cssClass));
        List <SelenideElement> elems = el.$$(By.tagName("div"));

        return new Slider() {
            private int i =0;

            @Override
            public void next() {
                System.out.println("next" + i);
                i++;
                if (i==elems.size()) i=0;
                elems.get(i).hover();
            }

            @Override
            public void previous() {
                i--;
                System.out.println("prev" + i);
                if (i<=0) i=0;
                elems.get(i).hover();

            }

            @Override
            public boolean goForwardTo(int num) {System.out.println("Не реализовано здесь.");return false;}

            @Override
            public void last() {System.out.println("Не реализовано здесь.");            }

            @Override
            public int size() {System.out.println("Не реализовано здесь.");return 0;}

            @Override
            public int current() {System.out.println("Не реализовано здесь.");return 0;}
        };

    }


    /**
     * Ищет на странице все элементы выбранного подкласса из enum
     */
    @Override
    public List<SelenideElement> findAll() {
        List<SelenideElement> list = $$x(xpath);
        //list.forEach(el->el.shouldBe(Condition.exist));   //очень долго, вкл только в крайнем случае
        return $$x(xpath);}

    /**
     * Ищет элемент с точным текстом
     * @param text имя поля
     */
    @Override
    public ElementsCollection find(String text) {
        return  $$x(xpath).filter(Condition.text(text));

    }
}

