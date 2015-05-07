package code;

import java.io.File;
import java.util.List;

public class Code {

    private List<CodeFile> components;
    
    private File file;
    
    public Code(String path) {
        this.file = new File(path);
        this.inspect();
    }
    
    private void inspect() {
        for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
                this.add(new Package(file));
            }
        }
    }

    private void add(CodeFile component) {
        this.components.add(component);
    }
}
