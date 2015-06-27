package code.config;

import org.json.simple.JSONObject;

import util.JsonHelper;
import code.project.ProjectMember;
import code.project.ProjectMethodMember;

public abstract class ConfigMember {
    
    private String name;

    private ConfigStatus status;

    private String test;
    
    public ConfigMember(ProjectMember projectMember, JSONObject oldConfigMember, ProjectMethodMember projectMethodMember) {
        this.name = projectMember.getNameWithParams();
        projectMember.setConfigMember(this);
        this.status = ConfigStatus.GENERATE;
        this.setStatus(JsonHelper.getString(oldConfigMember, "status"));
        this.test = projectMethodMember.getNameWithParams();
    }

    public String getName() {
        return this.name;
    }

    public void setStatus(String status) {
        for (ConfigStatus configStatus : ConfigStatus.values()) {
            if (status != null && status.equalsIgnoreCase(configStatus.name())) {
                this.status = configStatus;
            }
        }
    }
    
    public void setStatus(ConfigStatus status) {
        this.setStatus(status.name());
    }

    public ConfigStatus getStatus() {
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
        json.put("status", this.status.name());
        json.put("test", this.test);
        return json;
    }

    public abstract boolean isConfigConstructor();

    public abstract boolean isConfigMethod();

}
