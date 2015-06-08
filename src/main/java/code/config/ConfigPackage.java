package code.config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import code.Margin;

public class ConfigPackage extends ConfigCodeFile {
	private JSONObject jsonPackage;

	private ConfigPackage fatherPackage;

	private String name;

	private List<ConfigCodeFile> configCodeFileList;

	public ConfigPackage(JSONObject jsonPackage, ConfigPackage configPackage) {
		this.jsonPackage = jsonPackage;
		this.fatherPackage = configPackage;
		this.name = fatherPackage == null ? (String) this.jsonPackage.get("name") : fatherPackage.getName()
				+ "." + (String) this.jsonPackage.get("name");
		System.out.println(Margin.instance().tabs() + this.name);
		this.configCodeFileList = new ArrayList<ConfigCodeFile>();
		this.build();
	}

	public ConfigPackage(String name, ConfigPackage configPackage) {
		this.name = fatherPackage == null ? name : fatherPackage.getName() + "." + name;
		this.fatherPackage = configPackage;
	}

	private void build() {
		JSONArray packages = (JSONArray) this.jsonPackage.get("packages");
		if (packages != null) {
			for (Object jsonPackage : packages) {
				this.configCodeFileList.add(new ConfigPackage((JSONObject) jsonPackage, this));
			}
		}
		JSONArray clazzes = (JSONArray) this.jsonPackage.get("classes");
		if (clazzes != null) {
			for (Object jsonClazz : clazzes) {
				this.configCodeFileList.add(new ConfigClazz((JSONObject) jsonClazz, this));
			}
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
		configCodeFileList.get(configCodeFileList.indexOf(child)).add(child.getChild());
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
		if (!fatherPackage.equals(configPackage.fatherPackage))
			return false;
		if (!name.equals(configPackage.name))
			return false;
		return true;
	}
}
