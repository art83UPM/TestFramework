package code.config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import code.Margin;

public class ConfigClazz {
    private JSONObject jsonClazz;

    private ConfigPackage configPackage;

    private String name;

    private List<ConfigConstructorMember> configConstructorList;

    private List<ConfigMethodMember> configMethodList;

    public ConfigClazz(JSONObject jsonClazz, ConfigPackage configPackage) {
        this.jsonClazz = jsonClazz;
        this.configPackage = configPackage;
        this.name = (String) this.jsonClazz.get("name");
        Margin.instance().inc();
        System.out.println(Margin.instance().tabs() + this.name);
        this.configConstructorList = new ArrayList<ConfigConstructorMember>();
        this.configMethodList = new ArrayList<ConfigMethodMember>();
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
                this.configConstructorList.add(new ConfigConstructorMember((JSONObject) jsonConstructor, this));
            }
        }
        JSONArray jsonMethods = (JSONArray) this.jsonClazz.get("methods");
        if (jsonMethods != null) {
            for (Object jsonMethod : jsonMethods) {
                this.configMethodList.add(new ConfigMethodMember((JSONObject) jsonMethod, this));
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
            if (!configConstructorList.isEmpty()) {
                for (ConfigConstructorMember configConstructorMember : configConstructorList) {
                    if (configConstructorMember.equals(configMember)) {
                        System.out.println(Margin.instance().tabs() + "Siiiiii!!");
                        Margin.instance().dec();
                        return true;
                    }
                }
            }
            if (!configMethodList.isEmpty()) {
                for (ConfigMethodMember configMethodMember : configMethodList) {
                    if (configMethodMember.equals(configMember)) {
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
}
