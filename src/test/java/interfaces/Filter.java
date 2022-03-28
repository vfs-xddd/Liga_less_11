package interfaces;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

public interface Filter {

    List<Category> getCategories();
    List<Checkbox> getSwitchers();

}
