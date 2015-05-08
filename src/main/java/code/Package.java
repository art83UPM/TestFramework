package code;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Package extends CodeFile {
    
    private File file;
    
    private String name;
    
    private List<CodeFile> components;
    
    public Package(File file) {
        this(file, null);
    }

    public Package(File file, String fatherName) {
        this.file = file;
        this.name = fatherName == null ? this.file.getName() : fatherName+"."+this.file.getName();
        this.components = new ArrayList<CodeFile>();
        this.build();
    }

    public void build() {
        for (File file : this.file.listFiles()) {
            if (file.isDirectory()) {
                this.add(new Package(file, this.getName()));
            } else if (file.isFile()) {
                String className = this.name+"."+file.getName().split("\\.")[0];
                try {
                    System.out.println(className);
//                    this.add(new Clazz(Class.forName(className)));
                    this.add(new Clazz(TestFrameworkClassLoader.getClassLoader().loadClass(className)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void add(CodeFile component) {
        this.components.add(component);
    }
    
    public void remove(CodeFile component) {
        this.components.remove(component);
    }

    public List<CodeFile> getComponents() {
        return this.components;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public void accept(Visitor visitor) {
        for (CodeFile codeComponent : components) {
            codeComponent.accept(visitor);
        }
    }   
}
