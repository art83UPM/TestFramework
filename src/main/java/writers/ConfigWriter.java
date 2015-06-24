package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import code.Margin;
import code.config.ConfigCode;
import code.project.ProjectCode;

public class ConfigWriter {

	private String path;

    private ProjectCode main;
	
	private ProjectCode test;

	private File file;

	private ConfigCode configCodeOld;

	private ConfigCode configCodeNew;

	private BufferedWriter writer;

	public ConfigWriter(String path, ProjectCode main, ProjectCode test) {
		this.path = path;
		this.main = main;
		this.test = test;
		try {
			this.file = new File(this.path + File.separator + "config.json");
	        this.file.getParentFile().mkdirs();
			if (file.exists()) {
				File fileBack = new File(this.path + File.separator + "config.back" + ".json");
				//configCodeOld = new ConfigCode(this.path + File.separator + "config.json");
				Files.copy(file.toPath(), fileBack.toPath(), StandardCopyOption.REPLACE_EXISTING);				
			} else {
		         file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		configCodeNew = new ConfigCode(this.path + File.separator + "config.json", main, test);
	}

	public void writeNewConfigCode() {		
		try {
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(this.configCodeNew.toJson().toJSONString());
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
