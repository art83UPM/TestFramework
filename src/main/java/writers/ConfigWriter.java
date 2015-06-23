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

	private ProjectCode test;

	private File file;

	private ConfigCode configCodeOld;

	private ConfigCode configCodeNew;

	private BufferedWriter writer;

	public ConfigWriter(String path, ProjectCode main, ProjectCode test) {
		this.path = path;
		this.test = test;
		try {
			this.file = new File(this.path + File.separator + "config.json");
			if (!file.exists()) {
				file.createNewFile();
				writer = new BufferedWriter(new FileWriter(file));
				writer.write("{");
				writer.write("}");
				this.close();
			} else {
				File fileBack = new File(this.path + File.separator + "config.back" + ".json");
				configCodeOld = new ConfigCode(this.path + File.separator + "config.json");
				Files.copy(file.toPath(), fileBack.toPath(), StandardCopyOption.REPLACE_EXISTING);				
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
			writer.write("{");
			Margin.instance().inc();
			writer.write(Margin.instance().tabs() + "\n\"code\": {");
			Margin.instance().inc();
			writer.write(Margin.instance().tabs() + "\n\"packages\": [");
			configCodeNew.accept(this);
			writer.write(Margin.instance().tabs() + "\n]");
			Margin.instance().dec();
			writer.write(Margin.instance().tabs() + "\n}");
			Margin.instance().dec();
			writer.write("\n}");
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
