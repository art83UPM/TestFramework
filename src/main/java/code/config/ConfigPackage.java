package code.config;

import java.util.ArrayList;
import java.util.List;

import code.project.ProjectClazz;
import code.project.ProjectCodeFile;
import code.project.ProjectPackage;

public class ConfigPackage extends ConfigCodeFile {
    
    private ProjectPackage projectPackage;

	private ConfigPackage parentPackage;

	private List<ConfigCodeFile> components;
	
	public ConfigPackage(ProjectCodeFile projectPackage, ConfigPackage parentPackage) {
	    this.projectPackage = (ProjectPackage) projectPackage;
	    this.parentPackage = parentPackage;
	    this.name = this.projectPackage.getName();
	    this.components = new ArrayList<ConfigCodeFile>();
	    this.build();
	}

	private void build() {
		for (ProjectCodeFile component : this.projectPackage.getComponents()) {
            if (component instanceof ProjectPackage) {
                this.add(new ConfigPackage(component, this));
            } else if (component instanceof ProjectClazz){
                this.add(new ConfigClazz(component, this));
            }
        }
	}

	private void add(ConfigCodeFile configCodeFile) {
        this.components.add(configCodeFile);
    }

    public String getName() {
		return name;
	}

	public ConfigPackage getParentPackage() {
		return parentPackage;
	}
}
