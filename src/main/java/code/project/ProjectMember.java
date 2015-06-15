package code.project;

import java.lang.reflect.AccessibleObject;
import java.util.List;

import writers.Capitalizer;

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
    

	public String getNameWithParams() {
		String nameWithParams = this.getName();
        for (ProjectParameterMember parameterMember : this.getParametersType()) {
            nameWithParams += Capitalizer.capitalize(parameterMember.getType());
        }
        return nameWithParams;
	}

    public abstract void accept(Visitor visitor);
    
}
