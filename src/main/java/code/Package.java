package code;

import java.util.ArrayList;
import java.util.List;

public class Package extends CodeComponent {
    
    private String name;
    
    private List<CodeComponent> components;
    
    public Package(String name) {
        this.name = name;
        this.components = new ArrayList<CodeComponent>();
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
