package code;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Clazz extends CodeFile {

    private Class<?> clazz;
    
    private String name;

    private String myPackage;

    private List<ConstructorMember> constructorMemberList;

    private List<MethodMember> methodMemberList;

    public Clazz(Class<?> clazz) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
        myPackage = clazz.getPackage().getName();
        constructorMemberList = new ArrayList<ConstructorMember>();
        this.build();
    }

    public void build() {
        int order = 0;
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            ConstructorMember constructorMember = new ConstructorMember(constructor, order++);
            constructorMemberList.add(constructorMember);
        }
        methodMemberList = new ArrayList<MethodMember>();
        for (Method method : clazz.getDeclaredMethods()) {
            MethodMember methodMember = new MethodMember(method);
            methodMemberList.add(methodMember);
        }
    }

    public String getName() {
        return name;
    }

    public String getPackage() {
        return myPackage;
    }

    public List<ConstructorMember> getConstructorMemberList() {
        return constructorMemberList;
    }

    public List<MethodMember> getMethodMemberList() {
        return methodMemberList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (ConstructorMember constructorMember : constructorMemberList) {
            constructorMember.accept(visitor);
        }
        for (MethodMember methodMember : methodMemberList) {
            methodMember.accept(visitor);
        }
    }
}
