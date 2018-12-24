package util;

import java.util.Objects;

public class Assert {

    public static <T> void  notNull(final T t, final String errorMsg) {
        if(Objects.isNull(t)) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static <T> void  isTrue(final boolean exp, final String errorMsg) {
        if(!exp) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

}
