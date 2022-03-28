package interfaces;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

public interface EnumWeb {

    /**Ищет на странице все элементы выбранного подкласса из enum*/
    List<SelenideElement> findAll();

    /**Ищет элемент с точным текстом
     * @param text имя поля*/
     ElementsCollection find(String text);


}
