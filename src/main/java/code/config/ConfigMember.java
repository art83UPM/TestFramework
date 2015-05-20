package code.config;

import org.json.simple.JSONObject;

public class ConfigMember {
    protected JSONObject jsonMember;
    protected String name;
    protected String status;
    protected String test;
    
    public ConfigMember(JSONObject jsonMember) {
        this.jsonMember = jsonMember;
        this.name = (String) this.jsonMember.get("name");
        this.status = (String) this.jsonMember.get("status");
        this.test = (String) this.jsonMember.get("test");
    }

    public String getName() {
        return name;
    }   
}
