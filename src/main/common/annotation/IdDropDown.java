package main.common.annotation;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IdDropDown {
    Class<?> classtoJoin();
    String foreingkey();
    String todisplay();
}
