import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field fieid = r.getClass().getDeclaredFields()[0];
        fieid.setAccessible(true);
        System.out.println(fieid.getName());
        System.out.println(fieid.get(r));
        fieid.set(r, "new uuid");
        Method method = r.getClass().getDeclaredMethod("toString");
        method.invoke(r);
        System.out.println(method.invoke(r));
    }
}
