package code.project;

import java.lang.reflect.AccessibleObject;

public abstract class ProjectMember {
    
    protected AccessibleObject accessibleObject;

    public ProjectMember() {
    }

    public ProjectMember(AccessibleObject accessibleObject) {
        this.accessibleObject = accessibleObject;
    }

    public abstract String getName();

    public abstract void accept(Visitor visitor);
    
}
