package writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import code.config.ConfigCode;
import code.project.ProjectCode;

public class ConfigWriter {

    private ProjectCode test;

    private ConfigCode configCode;
    
    private Gson gson;
    
    private String path;
    
    private FileWriter writer;    

    public ConfigWriter(String path, ProjectCode main) {
        this.path = path;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.writeJson(main);
    }

    private void writeJson(ProjectCode main) {
        String json = gson.toJson(main);
        try {  
            FileWriter writer = new FileWriter(this.path +  File.separator + "config.json");  
            writer.write(json);  
            writer.close();  
             
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
