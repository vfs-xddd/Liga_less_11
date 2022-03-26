import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public enum Card {
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
    FEEDBACKS   ("product-rating__feedback","");


    public final String CSSclass;
    public final String xpath;

    Card(String CSSclass, String xpathAdd) {
        this.CSSclass = CSSclass;
        this.xpath = "//*[contains(@class,'" + CSSclass + "')]"+xpathAdd;
    }

    /**@param el контейнер слайдера с (@class,'product-picture-overlay')"*/
    public Slider slider(SelenideElement el){
        el.shouldHave(Condition.cssClass(SLIDER.CSSclass));
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
        };

    }

    /**Ищет на странице все элементы выбранного подкласса из enum*/
    public List<SelenideElement> findAll() {return $$x(xpath);}

    /**Ищет элемент с точным текстом*/
    public SelenideElement findText(String text) {
        return $$x(xpath).filter(Condition.exactText(text)).first();

    }

}

