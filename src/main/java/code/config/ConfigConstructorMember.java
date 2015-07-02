package code.config;

import org.json.simple.JSONObject;

import code.project.ProjectMember;
import code.project.ProjectMethodMember;

public class ConfigConstructorMember extends ConfigMember {

    public ConfigConstructorMember(ProjectMember projectMember, JSONObject oldConfigConstructor, ProjectMethodMember projectMethodMember) {
        super(projectMember, oldConfigConstructor, projectMethodMember);
    }
	
    @Override
	public boolean isConfigConstructor() {
		return true;
	}

	@Override
	public boolean isConfigMethod() {
		return false;
	}
}
