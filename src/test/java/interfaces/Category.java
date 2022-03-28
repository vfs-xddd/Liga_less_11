package interfaces;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

public interface Category {

    boolean isExpand();
    void collapse();
    void expand();
    List <Checkbox> getCheckboxList();
    SelenideElement searchField();
    void showAll();

}
