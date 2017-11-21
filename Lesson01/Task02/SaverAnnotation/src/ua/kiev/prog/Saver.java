package ua.kiev.prog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Saver {

    public static void save() {

        TextContainer container = new TextContainer();

        Class<?> cls = container.getClass();
        SaveTo saveTo = cls.getAnnotation(SaveTo.class);
        String path = saveTo.path();

        String text = "";

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
           if (field.getName().equals("text")) {
               field.setAccessible(true);
               try {
                   text = field.get(container).toString();
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
        }

        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Save.class)) {
                try {
                    method.invoke(container, path, text);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
