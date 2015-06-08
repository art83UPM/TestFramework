package code.project;

import java.lang.reflect.AccessibleObject;
import java.util.List;

public abstract class ProjectMember {
    
    protected AccessibleObject accessibleObject;
    
    protected String name;
    
    protected ProjectClazz projectClazz;
    
    protected List<ProjectParameterMember> parameterTypes;

    public ProjectMember() {
    }

    public ProjectMember(AccessibleObject accessibleObject, ProjectClazz projectClazz) {
        this.accessibleObject = accessibleObject;
        this.projectClazz = projectClazz;
    }

    public abstract String getName();
    
    public ProjectClazz getProjectClazz() {
        return this.projectClazz;
    }    

    public List<ProjectParameterMember> getParametersType() {
        return this.parameterTypes;
    }

    public abstract void accept(Visitor visitor);
    
}
