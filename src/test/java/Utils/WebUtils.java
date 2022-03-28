package Utils;

import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WebUtils {

    @SuppressWarnings("SameParameterValue")
    public static boolean elCssClassContains(SelenideElement el, String cssClass) {
        List<String> list = Arrays.asList((Objects.requireNonNull(el.getAttribute("class")).split(" ")));
        return list.contains(cssClass);

    }
}
