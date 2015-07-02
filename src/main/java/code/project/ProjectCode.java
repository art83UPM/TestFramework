package code.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import writers.ProjectVisitor;
import code.Margin;

public class ProjectCode implements CodeComponent {

    private File file;

    private List<ProjectPackage> components;

    public ProjectCode(String path) {
        this.file = new File(path);
        this.components = new ArrayList<ProjectPackage>();
        this.build();
    }

    private void build() {
        for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
                Margin.instance().inc();
                this.add(new ProjectPackage(file, null));
                Margin.instance().dec();
            }
        }
    }

    private void add(ProjectPackage component) {
        this.components.add(component);
    }

    public boolean exist(ProjectMember projectMember) {
        for (ProjectCodeFile codeFile : components) {
            if (codeFile.exist(projectMember)) {
                System.out.println(Margin.instance().tabs() + "Sí está!");
                return true;
            }
        }
        return false;
    }

    @Override
    public void accept(ProjectVisitor visitor) {
        for (ProjectCodeFile codeComponent : components) {
            codeComponent.accept(visitor);
        }        
    }

    public List<ProjectPackage> getComponents() {
        return this.components;
    }

    public ProjectPackage getPackage(String name) {
        for (ProjectPackage projectPackage : components) {
            if (projectPackage.getName().equals(name)) {
                return projectPackage;
            }
        }
        return null;
    }
}
