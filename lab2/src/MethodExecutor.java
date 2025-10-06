package lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MethodExecutor {

    private static final Map<Class<?>, Object> PARAMETER_VALUES = new HashMap<>();

    static {
        PARAMETER_VALUES.put(String.class, "NEW_USER");
        PARAMETER_VALUES.put(int.class, 28);
        PARAMETER_VALUES.put(Integer.class, 28);
        PARAMETER_VALUES.put(double.class, 28.3);
        PARAMETER_VALUES.put(Double.class, 28.3);
    }

    public void invokeAllMethods(Object target) {
        executeByAccessType(target, 0);
    }

    public void invokePublicMethods(Object target) {
        executeByAccessType(target, Modifier.PUBLIC);
    }

    public void invokePrivateMethods(Object target) {
        executeByAccessType(target, Modifier.PRIVATE);
    }

    public void invokeProtectedMethods(Object target) {
        executeByAccessType(target, Modifier.PROTECTED);
    }

    private void executeByAccessType(Object target, int accessType) {
        if (target == null) return;

        Class<?> targetClass = target.getClass();

        for (Method method : targetClass.getDeclaredMethods()) {
            RepeatableExecute annotation = method.getAnnotation(RepeatableExecute.class);

            if (annotation != null && checkAccess(method, accessType)) {
                processMethod(target, method, annotation);
            }
        }
    }

    private boolean checkAccess(Method method, int accessType) {
        int modifiers = method.getModifiers();

        if (accessType == 0) return true;
        if (accessType == Modifier.PUBLIC && Modifier.isPublic(modifiers)) return true;
        if (accessType == Modifier.PRIVATE && Modifier.isPrivate(modifiers)) return true;
        if (accessType == Modifier.PROTECTED && Modifier.isProtected(modifiers)) return true;

        return false;
    }

    private void processMethod(Object target, Method method, RepeatableExecute annotation) {
        System.out.println("Выполняется: " + method.getName());

        boolean accessChanged = false;
        try {
            if (!method.canAccess(target)) {
                method.setAccessible(true);
                accessChanged = true;
            }

            Object[] args = prepareMethodArgs(method);
            int repeatCount = annotation.count();

            for (int i = 0; i < repeatCount; i++) {
                method.invoke(target, args);
            }

        } catch (Exception e) {
            System.err.println("ОШИБКА ВЫПОЛНЕНИЯ " + method.getName() + ": " + e.getMessage());
        } finally {
            if (accessChanged) {
                method.setAccessible(false);
            }
        }
    }

    private Object[] prepareMethodArgs(Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();

        if (paramTypes.length == 0) {
            return null;
        }

        Object[] args = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            args[i] = PARAMETER_VALUES.get(paramTypes[i]);
            if (args[i] == null) {
                throw new RuntimeException("Неподдерживаемый тип параметра: " + paramTypes[i].getName());
            }
        }

        return args;
    }
}