package code.config;

public abstract class ConfigCodeFile {

    public abstract boolean exist(ConfigMember configMember);

    public abstract void add(ConfigCodeFile child);

    public abstract ConfigCodeFile getChild();

}
