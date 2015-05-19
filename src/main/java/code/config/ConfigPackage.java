package code.config;

import java.util.List;

public class ConfigPackage extends ConfigCodeFile{
    private List<ConfigCodeFile> configCodeFileList;

    public ConfigPackage(List<ConfigCodeFile> configCodeFileList) {
        this.configCodeFileList = configCodeFileList;
    } 
    
    
}
