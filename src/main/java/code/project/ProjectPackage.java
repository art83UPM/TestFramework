package code.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import code.Margin;
import code.TestFrameworkClassLoader;

public class ProjectPackage extends ProjectCodeFile {

    private File file;

    private List<ProjectCodeFile> components;

    public ProjectPackage(File file) {
        this(file, null);
    }

    public ProjectPackage(File file, String fatherName) {
        this.file = file;
        this.name = fatherName == null ? this.file.getName() : fatherName + "." + this.file.getName();
        this.components = new ArrayList<ProjectCodeFile>();
        System.out.println(Margin.instance().tabs() + "Paquete: " + this.name);
        this.build();
    }

    public ProjectPackage(String name) {
        this.name = name;
    }

    private void build() {
        for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
                Margin.instance().inc();
                this.add(new ProjectPackage(file, this.getName()));
                Margin.instance().dec();
            } else if (file.isFile()) {
                String className = this.name + "." + file.getName().split("\\.")[0];
                try {
                    Margin.instance().inc();
                    this.add(new ProjectClazz(TestFrameworkClassLoader.getClassLoader().loadClass(className)));
                    Margin.instance().dec();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void add(ProjectCodeFile component) {
        Margin.instance().inc();
        this.components.add(component);
        Margin.instance().dec();
    }

    public void remove(ProjectCodeFile component) {
        this.components.remove(component);
    }

    public List<ProjectCodeFile> getComponents() {
        return this.components;
    }

    @Override
    public void accept(Visitor visitor) {
        for (ProjectCodeFile codeComponent : components) {
            codeComponent.accept(visitor);
        }
    }

    @Override
    public boolean exist(ProjectMember projectMember, ProjectClazz projectClazz, ProjectPackage projectPackage) {
        System.out.println(Margin.instance().tabs() + "compruebo si en " + this.getName() + " está el método: " + projectMember.getName()
                + " --> ");
        if (this.name.equals(projectPackage.getName())) {
            for (ProjectCodeFile codeFile : components) {
                if (codeFile.exist(projectMember, projectClazz)) {
                    return true;
                }
            }
        } else {
            Margin.instance().inc();
            for (ProjectCodeFile codeFile : components) {
                if (codeFile instanceof ProjectPackage) {
                    Margin.instance().inc();
                    if (codeFile.exist(projectMember, projectClazz, projectPackage)) {
                        return true;
                    }
                    Margin.instance().dec();
                }
            }
        }
        Margin.instance().dec();
        System.out.println(Margin.instance().tabs() + "No está!");

        return false;
    }

    @Override
    public boolean exist(ProjectMember projectMember, ProjectClazz projectClazz) {
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
}
