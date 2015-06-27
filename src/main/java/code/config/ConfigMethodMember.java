package code.config;

import org.json.simple.JSONObject;

import code.project.ProjectMember;
import code.project.ProjectMethodMember;

public class ConfigMethodMember extends ConfigMember {

	public ConfigMethodMember(ProjectMember projectMember, JSONObject oldConfigMethod, ProjectMethodMember projectMethodMember) {
        super(projectMember, oldConfigMethod, projectMethodMember);
    }

    @Override
	public boolean isConfigConstructor() {
		return false;
	}

	@Override
	public boolean isConfigMethod() {
		return true;
	}
}
