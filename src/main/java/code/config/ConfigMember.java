package code.config;

import org.json.simple.JSONObject;

import code.Margin;

public class ConfigMember {
    protected JSONObject jsonMember;
    protected ConfigClazz configClazz;
    protected String name;
    protected String status;
    protected String test;
    
    public ConfigMember(JSONObject jsonMember, ConfigClazz configClazz) {
        this.jsonMember = jsonMember;
        this.configClazz = configClazz;
        this.name = (String) this.jsonMember.get("name");
        Margin.instance().inc();
        System.out.println(Margin.instance().tabs() + this.name);
        this.status = (String) this.jsonMember.get("status");
        this.test = (String) this.jsonMember.get("test");
        Margin.instance().dec();
    }
    
    public ConfigMember(String name, ConfigClazz configClazz) {
        this.configClazz = configClazz;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ConfigClazz getConfigClazz() {
        return this.configClazz;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTest(String test) {
        this.test = test;
    }       
}
