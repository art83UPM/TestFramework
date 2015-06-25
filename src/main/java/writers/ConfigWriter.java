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

    private File file;

    private ConfigCode configCodeOld;

    private ConfigCode configCodeNew;

    private BufferedWriter writer;

    public ConfigWriter(String path, ProjectCode main, ProjectCode test) {
        this.path = path;
        this.file = new File(this.path + File.separator + "config.json");
        if (file.exists()) {
            File fileBack = new File(this.path + File.separator + "config.back.json");
            try {
                Files.copy(file.toPath(), fileBack.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        configCodeNew = new ConfigCode(this.file, main, test);
    }

    public void writeNewConfigCode() {
        try {
            file.getParentFile().mkdirs();
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
