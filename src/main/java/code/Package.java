package code;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Package extends CodeComponent {
    
    private File file;
    
    private String name;
    
    private List<CodeComponent> components;
    
    public Package(File file) {
        this.file = file;
        this.name = this.file.getName();
        this.components = new ArrayList<CodeComponent>();
        this.inspect();
    }

    private void inspect() {
        for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
                this.add(new Package(file));
            } else if (file.isFile()) {
                String className = this.name+"."+file.getName().split(".")[0];
                //this.add(new Clazz(file.getPath()));
            }
        }
    }

    public void add(CodeComponent component) {
        this.components.add(component);
    }
    
    public void remove(CodeComponent component) {
        this.components.remove(component);
    }

    public List<CodeComponent> getComponents() {
        return this.components;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public void accept(Visitor visitor) {
        for (CodeComponent codeComponent : components) {
            codeComponent.accept(visitor);
        }
    }   
}
