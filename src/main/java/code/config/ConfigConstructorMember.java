package code.config;

import code.project.ProjectMember;
import code.project.ProjectMethodMember;

public class ConfigConstructorMember extends ConfigMember {

    public ConfigConstructorMember(ProjectMember projectMember, ConfigClazz configClazz, ProjectMethodMember projectMethodMember) {
        super(projectMember, configClazz);
        this.setTest(projectMethodMember.getNameWithParams());
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
