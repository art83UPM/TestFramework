package code.config;

import code.project.ProjectMember;

public class ConfigConstructorMember extends ConfigMember {

    public ConfigConstructorMember(ProjectMember projectMember, ConfigClazz configClazz) {
        super(projectMember, configClazz);
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
