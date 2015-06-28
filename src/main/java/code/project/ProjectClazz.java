package code.project;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import util.Capitalizer;
import code.Margin;
import code.config.ConfigClazz;

public class ProjectClazz extends ProjectCodeFile {

    private Class<?> clazz;

    private ProjectPackage projectPackage;

    private ConfigClazz configClazz;
    
    private List<ProjectConstructorMember> constructors;

    private List<ProjectMethodMember> methods;

    private List<ProjectMember> memberList;

    public ProjectClazz(Class<?> clazz, ProjectPackage projectPackage) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
        this.projectPackage = projectPackage;
        constructors = new ArrayList<ProjectConstructorMember>();
        methods = new ArrayList<ProjectMethodMember>();
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
            constructors.add(new ProjectConstructorMember(constructor, this));
            Margin.instance().dec();
        }
        for (Method method : clazz.getDeclaredMethods()) {
            Margin.instance().inc();
            methods.add(new ProjectMethodMember(method, this));
            Margin.instance().dec();
        }
        memberList.addAll(constructors);
        memberList.addAll(methods);
    }

    public ProjectPackage getProjectPackage() {
        return this.projectPackage;
    }

    public List<ProjectConstructorMember> getConstructorMemberList() {
        return constructors;
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
        return this.constructors;
    }

    public List<ProjectMethodMember> getMethods() {
        return this.methods;
    }

    public ProjectMethodMember getTestMethod(String name) {
        for (ProjectMethodMember method : methods) {
            if (method.getNameWithParams().equals("test" + Capitalizer.capitalize(name))) {
                return method;
            }
        }
        return null;
    }

    public void setConfigMember(ConfigClazz configClazz) {
        this.configClazz = configClazz;
    }
    
    public ConfigClazz getConfigMember() {
        return this.configClazz;
    }
}
