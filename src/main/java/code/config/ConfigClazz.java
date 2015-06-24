package code.config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import code.project.ProjectClazz;
import code.project.ProjectCodeFile;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;

public class ConfigClazz extends ConfigCodeFile {

    private ProjectClazz projectClazz;
    
    private ConfigPackage parentPackage;

    private List<ConfigMember> components;
    private List<ConfigConstructorMember> constructors;
    private List<ConfigMethodMember> methods;

    public ConfigClazz(ProjectCodeFile component, ConfigPackage parentPackage) {
        this.parentPackage = parentPackage;
        this.projectClazz = (ProjectClazz) component;
        this.name = this.projectClazz.getName();
        this.constructors = new ArrayList<ConfigConstructorMember>();
        this.methods = new ArrayList<ConfigMethodMember>();
        this.components = new ArrayList<ConfigMember>();
        this.build();
    }

    private void build() {
        for (ProjectConstructorMember component : this.projectClazz.getConstructors()) {
            this.constructors.add(new ConfigConstructorMember(component, this));
        }
        for (ProjectMethodMember component : this.projectClazz.getMethods()) {
            this.methods.add(new ConfigMethodMember(component, this));
        }
        components.addAll(this.constructors);
        components.addAll(this.methods);
    }
    
    public String getName() {
        return name;
    }
    
    @SuppressWarnings("unchecked")
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        JSONArray constructors = new JSONArray();
        for (ConfigConstructorMember constructor : this.constructors) {
            constructors.add(constructor.toJson());
        }
        json.put("constructors", constructors);
        JSONArray methods = new JSONArray();
        for (ConfigMethodMember method : this.methods) {
            methods.add(method.toJson());
        }
        json.put("methods", methods);
        return json;
    }

}
