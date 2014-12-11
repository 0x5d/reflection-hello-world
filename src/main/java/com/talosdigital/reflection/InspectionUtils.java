package com.talosdigital.reflection;

import java.util.HashSet;
import java.util.Set;

// Built from Jon Skeet's contribution to
// http://stackoverflow.com/questions/709961/determining-if-an-object-is-of-primitive-type
public class InspectionUtils {

	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isWrapperType(Class<?> objectClass)
    {
        return WRAPPER_TYPES.contains(objectClass);
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
}
