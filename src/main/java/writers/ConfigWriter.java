package writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import code.config.ConfigClazz;
import code.config.ConfigCode;
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

    // public void write() {
    //
    // try {
    // writer = new FileWriter(file);
    // writer.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(ProjectConstructorMember constructor) {

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
                // TODO ponerle el status del old
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
        // TODO Añadir configNew
    }

    @Override
    public void visit(ProjectClazz clazz) {
    }

    @Override
    public void visit(ProjectPackage projectPackage) {

    }
}
