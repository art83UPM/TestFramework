package code.project;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import code.Margin;

public class ProjectClazz extends ProjectCodeFile {

    private Class<?> clazz;

    private ProjectPackage projectPackage;

    private List<ProjectConstructorMember> constructorMemberList;

    private List<ProjectMethodMember> methodMemberList;
    
    private List<ProjectMember> memberList;

    public ProjectClazz(Class<?> clazz, ProjectPackage projectPackage) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
        this.projectPackage = projectPackage;
        constructorMemberList = new ArrayList<ProjectConstructorMember>();
        methodMemberList = new ArrayList<ProjectMethodMember>();
        memberList = new ArrayList<ProjectMember>();
        System.out.println(Margin.instance().tabs() + "Clase: " + this.name);
        this.build();
    }

    public ProjectClazz(String name, ProjectPackage projectPackage) {
        this.name = name;
        this.projectPackage = projectPackage;
    }

    private void build() {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            Margin.instance().inc();
            constructorMemberList.add(new ProjectConstructorMember(constructor, this));
            Margin.instance().dec();
        }
        for (Method method : clazz.getDeclaredMethods()) {
            Margin.instance().inc();        
            methodMemberList.add(new ProjectMethodMember(method, this));
            Margin.instance().dec();
        }
        memberList.addAll(constructorMemberList);
        memberList.addAll(methodMemberList);
    }

    public ProjectPackage getProjectPackage() {
        return this.projectPackage;
    }

    public List<ProjectConstructorMember> getConstructorMemberList() {
        return constructorMemberList;
    }

    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);
        for (ProjectMember member : memberList) {
            member.accept(visitor);
        }
        visitor.close();
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
    
    public String getPackagePath() {
    	return this.getProjectPackage().getName().replace(".", File.separator);
	}

    public List<ProjectConstructorMember> getConstructors() {
        return this.constructorMemberList;
    }
    
    public List<ProjectMethodMember> getMethods() {
        return this.methodMemberList;
    }
}
