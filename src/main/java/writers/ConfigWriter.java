package writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import code.config.ConfigCode;
import code.config.ConfigConstructorMember;
import code.config.ConfigMethodMember;
import code.project.ProjectClazz;
import code.project.ProjectCode;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.Visitor;

public class ConfigWriter implements Visitor {

    private String path;

    private ProjectCode test;

    private File file;

    private ConfigCode configCodeOld;

    private ConfigCode configCodeNew;

    private FileWriter writer;

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNewConfigCode() { //TODO dado el objeto configCodeNew, atributo de esta clase, imprimirlo en un fichero .json
    
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
        if (configCodeOld.exist(configConstructorMember)) {
            if (test.exist(constructor)) {
                configConstructorMember.setTest(constructor.getName() + "Test");
                configConstructorMember.setStatus("exist");
            } else {
                configConstructorMember.setTest(" ");
                configConstructorMember.setStatus(configCodeOld.getStatus(configConstructorMember));
            }
        } else {
            if (test.exist(constructor)) {
                configConstructorMember.setTest(constructor.getName() + "Test");
                configConstructorMember.setStatus("exist");                
            } else {
                configConstructorMember.setTest(" ");
                configConstructorMember.setStatus(" ");
            }
        }
        configCodeNew.add(configConstructorMember);
    }

    @Override
    public void visit(ProjectMethodMember method) {
        ConfigMethodMember configMethodMember = method.getConfigMethod();
        if (configCodeOld.exist(configMethodMember)) {
            if (test.exist(method)) {
                configMethodMember.setTest(method.getName() + "Test");
                configMethodMember.setStatus("exist");
            } else {
                configMethodMember.setTest(" ");
                configMethodMember.setStatus(configCodeOld.getStatus(configMethodMember));
            }
        } else {
            if (test.exist(method)) {
                configMethodMember.setTest(method.getName() + "Test");
                configMethodMember.setStatus("exist");                
            } else {
                configMethodMember.setTest(" ");
                configMethodMember.setStatus(" ");
            }
        }
        configCodeNew.add(configMethodMember);
    }

    @Override
    public void visit(ProjectClazz clazz) {
    }

    @Override
    public void visit(ProjectPackage projectPackage) {

    }
}
