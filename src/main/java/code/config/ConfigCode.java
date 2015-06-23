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

import code.project.ProjectCode;
import code.project.ProjectCodeFile;

public class ConfigCode {
    private File file;
    
    private List<ConfigCodeFile> packages;

    private JSONObject oldConfigCode;

    private ProjectCode main;

    private ProjectCode test;

    public ConfigCode(String path, ProjectCode main, ProjectCode test) {
        this.file = new File(path);
        this.packages = new ArrayList<ConfigCodeFile>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader(file));
            this.oldConfigCode = (JSONObject) json.get("code");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        this.main = main;
        this.test = test;
        this.build();
    }

    private void build() {
        for (ProjectCodeFile mainComponent : main.getComponents()) {
            this.add(new ConfigPackage(mainComponent, null));
        }
        //TODO test
        //TODO oldConfig
    }

    public void add(ConfigPackage component) {
        this.packages.add(component);
    }
}
