package code.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
                this.add(new ProjectPackage(file));
            }
        }
    }

    private void add(CodeFile component) {
        this.components.add(component);
    }
}
