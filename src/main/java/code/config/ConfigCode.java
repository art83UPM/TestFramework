package code.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import code.Margin;

public class ConfigCode {
    private File file;

    private JSONObject code;

    private List<ConfigPackage> configPackagesList;

    public ConfigCode(String path) {
        this.file = new File(path);
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(new FileReader(file));
            this.code = (JSONObject) json.get("code");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        this.configPackagesList = new ArrayList<ConfigPackage>();
        this.build();
    }

    private void build() {
        JSONArray packages = (JSONArray) this.code.get("packages");
        if (packages != null) {
            for (Object jsonPackage : packages) {
                this.configPackagesList.add(new ConfigPackage((JSONObject) jsonPackage, null));
            }
        }
    }

    public boolean exist(ConfigMember configMember) {
        for (ConfigPackage configPackage : configPackagesList) {
            if (configPackage.exist(configMember)) {
                System.out.println(Margin.instance().tabs() + "Sí está!");
                return true;
            }
        }
        return false;
    }

    public void add(ConfigMethodMember configMethodMember) {
        ConfigPackage configPackage = configMethodMember.getRoot(); //TODO metodo GETROOT en configMember
        if (!configPackagesList.contains(configPackage)) { // TODO metodo EQUALS en ConfigPackage
            configPackagesList.add(configPackage);
        }        
        configPackagesList.get(configPackagesList.indexOf(configPackage)).add(configPackage.getChild());
    }

	public String getStatus(ConfigMember configMember) {
		for (ConfigPackage configPackage : configPackagesList) {
            if (configPackage.exist(configMember)) {
            	return configPackage.getStatus(configMember);
            }
		}
		return " ";
	}

}
