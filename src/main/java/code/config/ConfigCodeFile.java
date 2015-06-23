package code.config;


public abstract class ConfigCodeFile extends ConfigCodeScope {
    
    protected String name;
    
    public String getName() {
        return this.name;
    }

    public abstract boolean exist(ConfigMember configMember);

    public abstract void add(ConfigCodeScope configCodeScope);

    public abstract ConfigCodeScope getChild();

	public abstract String getStatus(ConfigMember configMember);

}
