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
    
    public ConfigCode(String path){
        this.file = new File(path);
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(new FileReader(file));
            this.code = (JSONObject) json.get("code");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }        
        this.configPackagesList =new ArrayList<ConfigPackage>();
        this.build();
    }

    private void build() {
        JSONArray packages = (JSONArray) this.code.get("packages");
        if(!packages.isEmpty()) {
            for (Object jsonPackage : packages) {
               this.configPackagesList.add(new ConfigPackage((JSONObject) jsonPackage));
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
    
}
