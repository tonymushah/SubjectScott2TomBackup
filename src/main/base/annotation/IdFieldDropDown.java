package main.base.annotation;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IdFieldDropDown {
    Class<?> classtoJoin();
    String todisplay();
}
