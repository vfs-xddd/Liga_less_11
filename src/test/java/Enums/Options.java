package Enums;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import interfaces.EnumWeb;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public enum Options implements EnumWeb {
    FILTER_CONTAINER    ("*", "plp__filters", ""),
    CATEGORIES          ("label", "accordion__title", ""),
    CATEGORY_CONTAINER  ("*", "accordion__container", ""),
    OPTIONS_CONTAINER   ("*", "accordion__content", ""),
    CHECKBOX            ("*", "checkbox__content", ""),
    SWITCHER            ("*", "switcher__content", ""),
    ;

    public final String cssClass;
    public final String xpath;

    Options(String type, String cssClass, String xpathAdd) {

        this.cssClass = cssClass;
        this.xpath ="//"+type+"[contains(@class,'" + cssClass + "')]"+xpathAdd;
    }

    /**
     * Ищет на странице в блоке фильтров все элементы выбранного подкласса из enum
     */
    @Override
    public List<SelenideElement> findAll() {
        return $$x(FILTER_CONTAINER.xpath + xpath);}

    /**
     * Ищет элемент с точным текстом
     * @param text имя поля
     */
    @Override
    public ElementsCollection find(String text) {
        return $$x(FILTER_CONTAINER.xpath + xpath).filter(Condition.exactText(text));
    }

    public List<SelenideElement> findIn(SelenideElement elContainer) {
        return elContainer.$$x("."+xpath);}
}
