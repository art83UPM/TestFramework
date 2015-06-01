package code.config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import code.Margin;

public class ConfigClazz extends ConfigCodeFile {
    private JSONObject jsonClazz;

    private ConfigPackage configPackage;

    private String name;

    private List<ConfigMember> configMemberList;

    public ConfigClazz(JSONObject jsonClazz, ConfigPackage configPackage) {
        this.jsonClazz = jsonClazz;
        this.configPackage = configPackage;
        this.name = (String) this.jsonClazz.get("name");
        Margin.instance().inc();
        System.out.println(Margin.instance().tabs() + this.name);
        this.configMemberList = new ArrayList<ConfigMember>();
        this.build();
    }

    public ConfigClazz(String name, ConfigPackage configPackage) {
        this.name = name;
        this.configPackage = configPackage;
    }

    private void build() {
        JSONArray jsonConstructors = (JSONArray) this.jsonClazz.get("constructors");
        if (jsonConstructors != null) {
            for (Object jsonConstructor : jsonConstructors) {
                this.configMemberList.add(new ConfigConstructorMember((JSONObject) jsonConstructor, this));
            }
        }
        JSONArray jsonMethods = (JSONArray) this.jsonClazz.get("methods");
        if (jsonMethods != null) {
            for (Object jsonMethod : jsonMethods) {
                this.configMemberList.add(new ConfigMethodMember((JSONObject) jsonMethod, this));
            }
        }
        Margin.instance().dec();
    }

    public ConfigPackage getConfigPackage() {
        return configPackage;
    }

    public String getName() {
        return name;
    }

    public boolean exist(ConfigMember configMember) {
        Margin.instance().inc();
        System.out.println(Margin.instance().tabs() + "Compruebo si en la clase " + this.name + " esta el método: "
                + configMember.getName() + " -->");
        if (this.getName().equals(configMember.getConfigClazz().getName())) {
            if (!configMemberList.isEmpty()) {
                for (ConfigMember confirMember : configMemberList) {
                    if (confirMember.equals(configMember)) {
                        System.out.println(Margin.instance().tabs() + "Siiiiii!!");
                        Margin.instance().dec();
                        return true;
                    }
                }
            }
        }
        System.out.println(Margin.instance().tabs() + "No está!");
        Margin.instance().dec();
        return false;
    }

    public ConfigCodeScope getChild() {
        return this.configMemberList.get(0);
    }

	public ConfigPackage getRoot() {
		return this.getConfigPackage().getRoot();
	}

    @Override
    public void add(ConfigCodeScope child) {        
    }

	@Override
	public String getStatus(ConfigMember configMember) {
		if (this.getName().equals(configMember.getConfigClazz().getName())) {
            if (!configMemberList.isEmpty()) {
                for (ConfigMember confirMember : configMemberList) {
                    if (confirMember.equals(configMember)) {
                    	return confirMember.getStatus();
                    }
                }
            }
		}
		return null;
	}
}
