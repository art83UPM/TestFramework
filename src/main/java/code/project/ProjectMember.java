package code.project;

import java.lang.reflect.AccessibleObject;
import java.util.List;

import code.config.ConfigMember;
import util.Capitalizer;

public abstract class ProjectMember {
    
    protected AccessibleObject accessibleObject;
    
    protected String name;
    
    protected ProjectClazz projectClazz;
    
    protected List<ProjectParameterMember> parameterTypes;

    private ConfigMember configMember;

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

    public abstract void accept(ProjectVisitor visitor);

    public void setConfigMember(ConfigMember configMember) {
        this.configMember = configMember;
    }
    
    public ConfigMember getConfigMember() {
        return this.configMember;
    }
    
}
