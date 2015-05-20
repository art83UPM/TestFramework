package code.config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConfigClazz {
    private JSONObject jsonClazz;

    private String name;

    private List<ConfigConstructorMember> configConstructorList;

    private List<ConfigMethodMember> configMethodList;

    public ConfigClazz(JSONObject jsonClazz) {
        this.jsonClazz = jsonClazz;
        this.name = (String) this.jsonClazz.get("name");
        this.configConstructorList = new ArrayList<ConfigConstructorMember>();
        this.configMethodList = new ArrayList<ConfigMethodMember>();
        this.build();
    }

    private void build() {
        JSONArray jsonConstructors = (JSONArray) this.jsonClazz.get("methods");
        if (!jsonConstructors.isEmpty()) {
            for (Object jsonConstructor : jsonConstructors) {
                this.configConstructorList.add(new ConfigConstructorMember((JSONObject) jsonConstructor));
            }
        }
        JSONArray jsonMethods = (JSONArray) this.jsonClazz.get("methods");
        if (!jsonMethods.isEmpty()) {
            for (Object jsonMethod : jsonMethods) {
                this.configMethodList.add(new ConfigMethodMember((JSONObject) jsonMethod));
            }
        }
    }

    public boolean exist(ConfigMember configMember) {
        if(!configConstructorList.isEmpty()){
            for (ConfigConstructorMember configConstructorMember : configConstructorList) {
                if(configConstructorMember.equals(configMember)){
                    return true;
                }
            }
        }else if(!configMethodList.isEmpty()){
            for (ConfigMethodMember configMethodMember : configMethodList) {
                if(configMethodMember.equals(configMember)){
                    return true;
                }
            }
        }
        return false;
    }
}
