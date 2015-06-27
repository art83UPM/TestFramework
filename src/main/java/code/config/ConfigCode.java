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

import util.JsonHelper;
import code.project.ProjectCode;
import code.project.ProjectPackage;

public class ConfigCode {
    private List<ConfigPackage> packages;

    private JSONObject oldConfigCode;

    private ProjectCode main;

    private ProjectCode test;

    public ConfigCode(File file, ProjectCode main, ProjectCode test) {
        this.packages = new ArrayList<ConfigPackage>();
        if (file.exists()) {
            try {
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(new FileReader(file));
                this.oldConfigCode = (JSONObject) json.get("code");
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        this.main = main;
        this.test = test;
        this.build();
    }

    private void build() {
        JSONArray oldConfigPackages = (JSONArray) oldConfigCode.get("packages");
        for (ProjectPackage mainPackage : main.getComponents()) {
            this.add(new ConfigPackage(mainPackage, JsonHelper.getJsonObjectFromArray(mainPackage.getName(), oldConfigPackages), this.test.getPackage(mainPackage.getName())));
        }
    }

    public void add(ConfigPackage component) {
        this.packages.add(component);
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONObject code = new JSONObject();
        JSONArray packages = new JSONArray();
        for (ConfigPackage configPackage : this.packages) {
            packages.add(configPackage.toJson());
        }
        code.put("packages", packages);
        json.put("code", code);
        return json;
    }
}
