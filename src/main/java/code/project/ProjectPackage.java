package code.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import writers.ProjectVisitor;
import code.Margin;
import code.TestFrameworkClassLoader;

public class ProjectPackage extends ProjectCodeFile {

    private File file;

    private ProjectPackage fatherPackage;

    private List<ProjectPackage> packages;

    private List<ProjectClazz> clazzes;

    private List<ProjectCodeFile> components;

    public ProjectPackage(File file, ProjectPackage projectPackage) {
        this.file = file;
        this.fatherPackage = projectPackage;
        this.name = fatherPackage == null ? this.file.getName() : fatherPackage.getName() + "." + this.file.getName();
        this.packages = new ArrayList<ProjectPackage>();
        this.clazzes = new ArrayList<ProjectClazz>();
        this.components = new ArrayList<ProjectCodeFile>();
        System.out.println(Margin.instance().tabs() + "Paquete: " + this.name);
        this.build();
    }

    public ProjectPackage(String name, ProjectPackage projectPackage) {
        this.fatherPackage = projectPackage;
        this.name = fatherPackage == null ? name : fatherPackage.getName() + "." + name;
    }

    private void build() {
        for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
                Margin.instance().inc();
                this.packages.add(new ProjectPackage(file, this));
                Margin.instance().dec();
            } else if (file.isFile() && file.getName().endsWith(".class")) {
                String className = this.name + "." + file.getName().split("\\.")[0];
                try {
                    Margin.instance().inc();
                    this.clazzes.add(new ProjectClazz(TestFrameworkClassLoader.getClassLoader().loadClass(className), this));
                    Margin.instance().dec();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        this.components.addAll(this.packages);
        this.components.addAll(this.clazzes);
    }

    public void remove(ProjectCodeFile component) {
        this.components.remove(component);
    }

    public List<ProjectCodeFile> getComponents() {
        return this.components;
    }

    public List<ProjectPackage> getPackages() {
        return this.packages;
    }

    public List<ProjectClazz> getClazzes() {
        return this.clazzes;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        visitor.visit(this);
        for (ProjectCodeFile codeComponent : components) {
            codeComponent.accept(visitor);
        }
    }

    @Override
    public boolean exist(ProjectMember projectMember) {
        System.out.println(Margin.instance().tabs() + "compruebo si en " + this.getName() + " está el método: " + projectMember.getName()
                + " --> ");
        Margin.instance().inc();
        if (projectMember.getProjectClazz().getProjectPackage().getName().contains(this.getName())) {
            for (ProjectCodeFile codeFile : components) {
                if (codeFile.exist(projectMember)) {
                    return true;
                }
            }
        } else {
            Margin.instance().dec();
        }
        System.out.println(Margin.instance().tabs() + "No está!");
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        ProjectPackage projectPackage = (ProjectPackage) obj;
        if (!name.equals(projectPackage.getName())) {
            return false;
        }
        return true;
    }

    public ProjectPackage getPackage(String name) {
        for (ProjectPackage projectPackage : packages) {
            if (projectPackage.getName().equals(name)) {
                return projectPackage;
            }
        }
        return null;
    }

    public ProjectClazz getClazz(String name) {
        for (ProjectClazz clazz : clazzes) {
            if (clazz.getName().equals(name + "Test")) {
                return clazz;
            }
        }
        return null;
    }
}
