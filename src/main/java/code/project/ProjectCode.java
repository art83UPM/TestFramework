package code.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import code.Margin;

public class ProjectCode {

    private File file;

    private List<CodeFile> components;

    public ProjectCode(String path) {
        this.file = new File(path);
        this.components = new ArrayList<CodeFile>();
        this.build();
    }

    private void build() {
        for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
                Margin.instance().inc();
                this.add(new ProjectPackage(file));
                Margin.instance().dec();
            }
        }
    }

    private void add(CodeFile component) {
        this.components.add(component);
    }

    public boolean exist(ProjectMember projectMember, ProjectClazz projectClazz, ProjectPackage projectPackage) {
        for (CodeFile codeFile : components) {
            if (codeFile.exist(projectMember, projectClazz, projectPackage)) {
                System.out.println(Margin.instance().tabs() + "Sí está!");
                return true;
            }
        }
        return false;
    }
}
