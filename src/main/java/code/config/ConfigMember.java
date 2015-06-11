package code.config;

import org.json.simple.JSONObject;

import writers.ConfigWriter;
import code.Margin;
import code.project.ProjectCode;
import code.project.ProjectMember;

public abstract class ConfigMember extends ConfigCodeScope {
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

    public String getStatus() {
		return status;
	}

    public void setTest(String test) {
        this.test = test;
    }

	public String getTest() {
		return test;
	}

	public ConfigPackage getRoot() {		
		return this.getConfigClazz().getRoot();
	}
	
	public void setTestAndStatus(ConfigCode configCodeOld, ProjectCode test, ProjectMember member) {
		if (configCodeOld.exist(this)) {
            if (test.exist(member)) {
                this.setTest(member.getName() + "Test");
                this.setStatus("exist");
            } else {
                this.setTest(" ");
                this.setStatus(configCodeOld.getStatus(this));
            }
        } else {
            if (test.exist(member)) {
                this.setTest(member.getName() + "Test");
                this.setStatus("exist");                
            } else {
                this.setTest(" ");
                this.setStatus(" ");
            }
        }
	}

	@Override
	public ConfigCodeScope getChild() {
		return null;
	}

	public abstract void accept(ConfigWriter configWriter);

	public abstract boolean isConfigConstructor();

	public abstract boolean isConfigMethod();	
	
}
