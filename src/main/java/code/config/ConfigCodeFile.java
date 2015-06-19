package code.config;

import writers.ConfigWriter;

public abstract class ConfigCodeFile extends ConfigCodeScope implements ConfigComponent {

    public abstract boolean exist(ConfigMember configMember);

    public abstract void add(ConfigCodeScope configCodeScope);

    public abstract ConfigCodeScope getChild();

	public abstract String getStatus(ConfigMember configMember);

	public abstract void accept(ConfigWriter configWriter);
}
