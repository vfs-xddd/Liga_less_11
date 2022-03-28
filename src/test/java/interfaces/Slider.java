package interfaces;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

public interface Slider {
    void next();
    void previous();
    void last();

    int size();

    @CanIgnoreReturnValue
    boolean goForwardTo(int num);

    @CanIgnoreReturnValue
    int current();





}
