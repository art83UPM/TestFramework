package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import code.config.ConfigCode;
import code.project.ProjectCode;

public class ConfigWriter {

    private File file;

    private ConfigCode configCode;

    private BufferedWriter writer;

    public ConfigWriter(String path, ProjectCode main, ProjectCode test) {
        this.file = new File(path + File.separator + "config.json");
        if (file.exists()) {
            File fileBack = new File(path + File.separator + "config.back.json");
            try {
                Files.copy(file.toPath(), fileBack.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        configCode = new ConfigCode(this.file, main, test);
    }

    public ConfigCode getConfigCode() {
        return this.configCode;
    }
    
    public void writeNewConfigCode() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(this.configCode.toJson().toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
