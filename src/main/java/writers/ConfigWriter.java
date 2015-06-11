package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import code.Margin;
import code.config.ConfigClazz;
import code.config.ConfigCode;
import code.config.ConfigConstructorMember;
import code.config.ConfigMember;
import code.config.ConfigMethodMember;
import code.config.ConfigPackage;
import code.config.ConfigVisitor;
import code.project.ProjectClazz;
import code.project.ProjectCode;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.ProjectVisitor;

public class ConfigWriter implements ProjectVisitor, ConfigVisitor {

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
			} else {
				File fileBack = new File(this.path + File.separator + "config.back" + ".json");
				configCodeOld = new ConfigCode(this.path + File.separator + "config.json");
				Files.copy(file.toPath(), fileBack.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeNewConfigCode() {
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("{");
			Margin.instance().inc();
			writer.write(Margin.instance().tabs() + "code: {");
			Margin.instance().inc();
			writer.write(Margin.instance().tabs() + "packages: [");
			configCodeNew.accept(this);
			writer.write(Margin.instance().tabs() + "]");
			Margin.instance().dec();
			writer.write(Margin.instance().tabs() + "}");
			Margin.instance().dec();
			writer.write("}");
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

	@Override
	public void visit(ProjectConstructorMember constructor) {
		ConfigConstructorMember configConstructorMember = constructor.getConfigConstructor();
		configConstructorMember.setTestAndStatus(configCodeOld, test, constructor);
		configCodeNew.add(configConstructorMember);
	}

	@Override
	public void visit(ProjectMethodMember method) {
		ConfigMethodMember configMethodMember = method.getConfigMethod();
		configMethodMember.setTestAndStatus(configCodeOld, test, method);
		configCodeNew.add(configMethodMember);
	}

	@Override
	public void visit(ProjectClazz clazz) {
	}

	@Override
	public void visit(ProjectPackage projectPackage) {

	}

	@Override
	public void visit(ConfigPackage configPackage) {
		try {
			Margin.instance().inc();
			writer.write(Margin.instance().tabs() + "{");
			writer.write(Margin.instance().tabs() + "name: " + configPackage.getName() + ",");
			writer.write(Margin.instance().tabs() + "classes: [");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visitPackageBack() {
		try {
			writer.write(Margin.instance().tabs() + "]");
			writer.write(Margin.instance().tabs() + "},");
			Margin.instance().dec();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(ConfigClazz configClazz) {
		try {
			Margin.instance().inc();
			writer.write(Margin.instance().tabs() + "{");
			writer.write(Margin.instance().tabs() + " name: " + configClazz.getName() + ",");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visitClassBack() {
		try {
			writer.write(Margin.instance().tabs() + "},");
			Margin.instance().dec();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(ConfigMember configMember) {
		try {
			Margin.instance().inc();
			writer.write(Margin.instance().tabs() + "{");
			writer.write(Margin.instance().tabs() + " name: " + configMember.getName() + ",");
			writer.write(Margin.instance().tabs() + " status: " + configMember.getStatus() + ",");
			writer.write(Margin.instance().tabs() + " test: " + configMember.getTest() + ",");
			writer.write(Margin.instance().tabs() + "},");
			Margin.instance().dec();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void visitFirstMember(String memberSet) {
		try {
			writer.write(Margin.instance().tabs() + " " + memberSet + ": [");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void visitLastMember() {
		try {
			writer.write(Margin.instance().tabs() + "],");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
