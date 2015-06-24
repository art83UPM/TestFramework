package code.config;

import org.json.simple.JSONObject;

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
        this.test = "";
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
    
    @SuppressWarnings("unchecked")
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("status", this.status);
        json.put("test", this.test);
        return json;
    }

    public abstract boolean isConfigConstructor();

    public abstract boolean isConfigMethod();

}
