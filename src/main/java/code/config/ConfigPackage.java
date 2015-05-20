package code.config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConfigPackage {
    private JSONObject jsonPackage;

    private String name;

    private List<ConfigPackage> configPackagesList;

    private List<ConfigClazz> configClazzesList;

    public ConfigPackage(JSONObject jsonPackage) {        
        this.jsonPackage = jsonPackage;
        this.name = (String) this.jsonPackage.get("name");
        this.configPackagesList = new ArrayList<ConfigPackage>();
        this.configClazzesList = new ArrayList<ConfigClazz>();
        this.build();
    }

    private void build() {
        JSONArray packages = (JSONArray) this.jsonPackage.get("packages");
        if (!packages.isEmpty()) {
            for (Object jsonPackage : packages) {
                this.configPackagesList.add(new ConfigPackage((JSONObject) jsonPackage));
            }
        }
        JSONArray clazzes = (JSONArray) this.jsonPackage.get("classes");
        if (!clazzes.isEmpty()) {
            for (Object jsonClazz : clazzes) {
                this.configClazzesList.add(new ConfigClazz((JSONObject) jsonClazz));
            }
        }
    }

    public boolean exist(ConfigMember configMember) {
        if(!configClazzesList.isEmpty()){
            for (ConfigClazz configClazz : configClazzesList) {
                if(configClazz.exist(configMember)) {
                    return true;
                }
            }
        }else if(!configPackagesList.isEmpty()){
            for (ConfigPackage configPackage : configPackagesList) {
                if (configPackage.exist(configMember)) {
                    return true;
                }
            }
        }
        return false;
    }

}
