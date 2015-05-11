package code.project;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ProjectClazz extends CodeFile {

    private Class<?> clazz;
    
    private String name;

    private String myPackage;

    private List<ProjectConstructorMember> constructorMemberList;

    private List<ProjectMethodMember> methodMemberList;

    public ProjectClazz(Class<?> clazz) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
        myPackage = clazz.getPackage().getName();
        constructorMemberList = new ArrayList<ProjectConstructorMember>();
        this.build();
    }

    public void build() {
        int order = 0;
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            ProjectConstructorMember constructorMember = new ProjectConstructorMember(constructor, order++);
            constructorMemberList.add(constructorMember);
        }
        methodMemberList = new ArrayList<ProjectMethodMember>();
        for (Method method : clazz.getDeclaredMethods()) {
            ProjectMethodMember methodMember = new ProjectMethodMember(method);
            methodMemberList.add(methodMember);
        }
    }

    public String getName() {
        return name;
    }

    public String getPackage() {
        return myPackage;
    }

    public List<ProjectConstructorMember> getConstructorMemberList() {
        return constructorMemberList;
    }

    public List<ProjectMethodMember> getMethodMemberList() {
        return methodMemberList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (ProjectConstructorMember constructorMember : constructorMemberList) {
            constructorMember.accept(visitor);
        }
        for (ProjectMethodMember methodMember : methodMemberList) {
            methodMember.accept(visitor);
        }
    }
}
