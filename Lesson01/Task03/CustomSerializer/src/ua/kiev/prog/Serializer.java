package Lesson01.Task3;

import java.lang.reflect.Field;
import java.lang.reflect.Type;


public class Serializer {

    public static String serialize(Object obj) {

        StringBuilder sb = new StringBuilder();
        Object value;

        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            if(field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                try {
                    value = field.get(obj);
                    sb.append(field.getName()).append("=").append(value).append(",");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        sb.append(";");

        return sb.toString();
    }

    public static <T> T deserialize(String str, Class<T> cls) throws IllegalAccessException, InstantiationException {

        T result = (T)cls.newInstance();

        String[] fields = str.split(",");
        for(String f : fields) {
            String[] arr = f.split("=");
            String key = arr[0];
            String value = arr[1];

            try {
                Field field = cls.getDeclaredField(key);
                if(field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    Type type = field.getType();
                    field.set(result, type == int.class ? Integer.parseInt(value) : value);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
