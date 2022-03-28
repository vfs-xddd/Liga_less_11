import interfaces.Category;
import interfaces.Checkbox;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Steps {

    @Step
    @DisplayName("установить чекбокс")
    public void checkbox() {
        List<Category> categories = SearchingPage.filter().getCategories();
        List<Checkbox> checkboxes = categories.get(0).getCheckboxList();
        checkboxes.get(0).on();
    }

    @Step
    @DisplayName("включить радио")
    public void radio() {
        List<Checkbox> switchers = SearchingPage.filter().getSwitchers();
        switchers.get(0).on();
    }

    @Step
    @DisplayName("найти карточку товара")
    public void find() {
        SearchingPage.findProduct("Телевизор Hisense 65A7GQ");
    }

}
