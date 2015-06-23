package code.config;

import code.project.ProjectMember;

public class ConfigMethodMember extends ConfigMember {

	public ConfigMethodMember(ProjectMember projectMember, ConfigClazz configClazz) {
        super(projectMember, configClazz);
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
