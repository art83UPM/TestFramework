package code.config;

import code.project.ProjectMember;
import code.project.ProjectMethodMember;

public class ConfigMethodMember extends ConfigMember {

	public ConfigMethodMember(ProjectMember projectMember, ConfigClazz configClazz, ProjectMethodMember projectMethodMember) {
        super(projectMember, configClazz);
        this.setTest(projectMethodMember.getNameWithParams());
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
