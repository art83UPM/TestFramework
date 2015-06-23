package code.config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import code.Margin;
import code.project.ProjectCodeFile;
import code.project.ProjectPackage;

public class ConfigPackage extends ConfigCodeFile {
    
    private ProjectPackage projectPackage;

	private ConfigPackage fatherPackage;

	private List<ConfigCodeFile> components;
	
	public ConfigPackage(ProjectCodeFile projectPackage, ConfigPackage parentPackage) {
	    this.projectPackage = (ProjectPackage) projectPackage;
	    this.fatherPackage = parentPackage;
	    this.name = this.projectPackage.getName().split(".")[this.projectPackage.getName().length()-1];
	    this.components = new ArrayList<ConfigCodeFile>();
	    this.build();
	}

	private void build() {
		for (ProjectCodeFile component : this.projectPackage.getComponents()) {
            
        }
	}

	public String getName() {
		return name;
	}

	public ConfigPackage getFatherPackage() {
		return fatherPackage;
	}

	public boolean exist(ConfigMember configMember) {
		System.out.println(Margin.instance().tabs() + "Compruebo si en el paquete " + this.name
				+ " esta el método: " + configMember.getName() + " -->");
		if (configMember.getConfigClazz().getConfigPackage().getName().contains(this.getName())) {
			if (!configCodeFileList.isEmpty()) {
				for (ConfigCodeFile configCodeFile : configCodeFileList) {
					if (configCodeFile.exist(configMember)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public ConfigCodeFile getChild() {
		return this.configCodeFileList.get(0);
	}

	public ConfigPackage getRoot() {		
		if (this.getFatherPackage() != null) {
			fatherPackage.add(this);
			return fatherPackage;
		} else {
			return this;
		}
	}

	@Override
	public void add(ConfigCodeScope child) {
		if (!this.configCodeFileList.contains(child)) {
			configCodeFileList.add((ConfigCodeFile) child);
		}
		//TODO configCodeFileList.get(configCodeFileList.indexOf(child)).add(child.getChild());
	}

	@Override
	public String getStatus(ConfigMember configMember) {
		if (configMember.getConfigClazz().getConfigPackage().getName().contains(this.getName())) {
			if (!configCodeFileList.isEmpty()) {
				for (ConfigCodeFile configCodeFile : configCodeFileList) {
					if (configCodeFile.exist(configMember)) {
						return configCodeFile.getStatus(configMember);
					}
				}
			}
		}
		return " ";
	}
	
	@Override
	public boolean equals(Object obj) {
		ConfigPackage configPackage = (ConfigPackage) obj;
		if (fatherPackage == null) {
			if (configPackage.fatherPackage != null)
				return false;
		} else if (!fatherPackage.equals(configPackage.fatherPackage))
			return false;
		if (name == null) {
			if (configPackage.name != null)
				return false;
		} else if (!name.equals(configPackage.name))
			return false;
		return true;		
	}
}
