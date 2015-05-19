package code.project;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import code.Margin;

public class ProjectClazz extends ProjectCodeFile {

    private Class<?> clazz;

    private String myPackage;

    private List<ProjectMember> memberList;

    public ProjectClazz(Class<?> clazz) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
        myPackage = clazz.getPackage().getName();
        memberList = new ArrayList<ProjectMember>();
        System.out.println(Margin.instance().tabs() + "Clase: " + this.name);
        this.build();
    }

    public ProjectClazz(String name) {
        this.name = name;
    }

    private void build() {
        int order = 0;
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            Margin.instance().inc();
            ProjectConstructorMember constructorMember = new ProjectConstructorMember(constructor, order++);
            memberList.add(constructorMember);
            Margin.instance().dec();
        }
        for (Method method : clazz.getDeclaredMethods()) {
            Margin.instance().inc();
            ProjectMethodMember methodMember = new ProjectMethodMember(method);
            memberList.add(methodMember);
            Margin.instance().dec();
        }
    }

    public String getPackage() {
        return myPackage;
    }

    public List<ProjectMember> getConstructorMemberList() {
        return memberList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (ProjectMember member : memberList) {
            member.accept(visitor);
        }
    }

    @Override
    public boolean exist(ProjectMember projectMember, ProjectClazz projectClazz, ProjectPackage projectPackage) {
        return false;
    }

    @Override
    public boolean exist(ProjectMember projectMember, ProjectClazz projectClazz) {
        Margin.instance().inc();
        System.out.println(Margin.instance().tabs() + "compruebo si en la clase " + this.getName() + " está el método: "
                + projectMember.getName() + " --> ");
        if (this.getName().equals(projectClazz.getName())) {
            for (ProjectMember projectMemberIt : memberList) {
                if (projectMember.equals(projectMemberIt)) {
                    System.out.println(Margin.instance().tabs() + "Si estaba!");
                    Margin.instance().dec();
                    return true;
                }
            }
        }
        System.out.println(Margin.instance().tabs() + "No está, los nombres de las clases no coinciden: [" + this.getName() + " - "
                + projectClazz.getName() + "]");
        Margin.instance().dec();
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        ProjectClazz projectClazz = (ProjectClazz) obj;
        if (!name.equals(projectClazz.getName())) {
            return false;
        }
        return true;
    }
}
