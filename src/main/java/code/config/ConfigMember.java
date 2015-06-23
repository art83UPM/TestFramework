package code.config;

import util.Capitalizer;
import code.project.ProjectMember;

public abstract class ConfigMember {

    private ConfigClazz parentClazz;

    private ProjectMember projectMember;
    
    private String name;

    private String status;

    private String test;
    
    public ConfigMember(ProjectMember projectMember, ConfigClazz configClazz) {
        this.parentClazz = configClazz;
        this.projectMember = projectMember;
        this.name = projectMember.getNameWithParams();
        this.status = "default";
        this.test = "test" + Capitalizer.capitalize(projectMember.getNameWithParams());
    }

    public String getName() {
        return this.name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public abstract boolean isConfigConstructor();

    public abstract boolean isConfigMethod();

}
