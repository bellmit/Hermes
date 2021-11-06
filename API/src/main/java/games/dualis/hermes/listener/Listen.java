package games.dualis.hermes.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a given {@link Listener}.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Listen {

    /**
     * Returns the priority for marked {@link Listener}.
     *
     * @return the priority
     */
    int value() default 0;

}
