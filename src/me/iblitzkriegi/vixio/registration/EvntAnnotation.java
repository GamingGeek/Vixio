package me.iblitzkriegi.vixio.registration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Blitz on 10/30/2016.
 */
public class EvntAnnotation {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Event {
        String name();
        Class type();
        String syntax();
    }

}
