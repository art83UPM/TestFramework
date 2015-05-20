package code.project;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import code.Margin;

public class ProjectClazz extends ProjectCodeFile {

    private Class<?> clazz;

    private ProjectPackage projectPackage;

    private List<ProjectMember> memberList;

    public ProjectClazz(Class<?> clazz, ProjectPackage projectPackage) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
        this.projectPackage = projectPackage;
        memberList = new ArrayList<ProjectMember>();
        System.out.println(Margin.instance().tabs() + "Clase: " + this.name);
        this.build();
    }

    public ProjectClazz(String name, ProjectPackage projectPackage) {
        this.name = name;
        this.projectPackage = projectPackage;
    }

    private void build() {
        int order = 0;
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            Margin.instance().inc();
            memberList.add(new ProjectConstructorMember(constructor, order++, this));
            Margin.instance().dec();
        }
        for (Method method : clazz.getDeclaredMethods()) {
            Margin.instance().inc();        
            memberList.add(new ProjectMethodMember(method, this));
            Margin.instance().dec();
        }
    }

    public ProjectPackage getProjectPackage() {
        return this.projectPackage;
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
    public boolean exist(ProjectMember projectMember) {        
        System.out.println(Margin.instance().tabs() + "compruebo si en la clase " + this.getName() + " está el método: "
                + projectMember.getName() + " --> ");
        if (this.getName().equals(projectMember.getProjectClazz().getName())) {
            for (ProjectMember projectMemberIt : memberList) {
                if (projectMember.equals(projectMemberIt)) {
                    System.out.println(Margin.instance().tabs() + "Si estaba!");
                    Margin.instance().dec();
                    return true;
                }
            }
        }
        System.out.println(Margin.instance().tabs() + "No está, los nombres de las clases no coinciden: [" + this.getName() + " - "
                + projectMember.getProjectClazz().getName() + "]");
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        ProjectClazz projectClazz = (ProjectClazz) obj;
        if (!projectPackage.equals(projectClazz.getProjectPackage())) {
            return false;
        }
        if (!name.equals(projectClazz.getName())) {
            return false;
        }
        return true;
    }
}
