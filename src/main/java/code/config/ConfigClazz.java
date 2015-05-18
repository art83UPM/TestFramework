package code.config;

import java.util.List;

public class ConfigClazz extends ConfigCodeFile{
    private List<ConfigMember> configMemberList;

    public ConfigClazz(List<ConfigMember> configMemberList) {
        this.configMemberList = configMemberList;
    }        
}
