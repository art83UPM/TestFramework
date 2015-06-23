package code.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import code.Margin;
import code.TestFrameworkClassLoader;

public class ProjectPackage extends ProjectCodeFile {

    private File file;

    private ProjectPackage fatherPackage;

    private List<ProjectCodeFile> components;

    public ProjectPackage(File file, ProjectPackage projectPackage) {
        this.file = file;
        this.fatherPackage = projectPackage;
        this.name = fatherPackage == null ? this.file.getName() : fatherPackage.getName() + "." + this.file.getName();
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
                this.add(new ProjectPackage(file, this));
                Margin.instance().dec();
            } else if (file.isFile() && file.getName().endsWith(".class")) {
                String className = this.name + "." + file.getName().split("\\.")[0];
                try {
                    Margin.instance().inc();
                    this.add(new ProjectClazz(TestFrameworkClassLoader.getClassLoader().loadClass(className), this));
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
        }else{
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
}
