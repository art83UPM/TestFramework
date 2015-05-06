package spike;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestClassGenerator {
    // DataWriter dataWriter;

    public void generate(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);

            System.out.println("~~~~~~ "+ clazz.getPackage() + " ~~~~~~");  
            System.out.println("~~~~~~ Class "+ clazz.getSimpleName() + " ~~~~~~");            
            this.printConstructors(this.getConstructors(clazz));
            this.printMethods(this.getMethods(clazz));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printConstructors(Constructor<?>[] constructors) {
        System.out.println("Constructors:");
        for (Constructor<?> constructor : constructors) {
            System.out.print(Modifier.toString(constructor.getModifiers()) + " " + constructor.getName() + "(");
            this.printParameters(this.getParametersType(constructor));
            System.out.println(")");
        }
    }

    private Class<?>[] getParametersType(Constructor<?> constructor) {
        return constructor.getParameterTypes();
    }

    private Constructor<?>[] getConstructors(Class<?> clazz) {
        return clazz.getConstructors();
    }

    private void printMethods(Method[] methods) {
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.print(Modifier.toString(method.getModifiers()) + " " + method.getReturnType().toString() + " " + method.getName()
                    + "(");
            this.printParameters(this.getParametersType(method));
            System.out.println(")");
        }
    }

    private void printParameters(Class<?>[] classes) {
        for (int i = 0; i < classes.length; i++) {
            if (i == classes.length - 1) {
                System.out.print(classes[i].getName());
            } else {
                System.out.print(classes[i].getName() + ", ");
            }
        }
    }

    private Class<?>[] getParametersType(Method method) {
        return method.getParameterTypes();
    }

    private Method[] getMethods(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    public static void main(String[] args) {
        new TestClassGenerator().generate(args[0]);
    }
}
