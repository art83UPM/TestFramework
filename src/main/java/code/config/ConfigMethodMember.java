package code.config;

import org.json.simple.JSONObject;

import writers.ConfigWriter;
import code.Margin;
import code.project.ProjectCode;
import code.project.ProjectMethodMember;

public class ConfigMethodMember extends ConfigMember {

    public ConfigMethodMember(JSONObject jsonMethod, ConfigClazz configClazz) {
        super(jsonMethod, configClazz);
    } 

    public ConfigMethodMember(String name, ConfigClazz configClazz) {
        super(name, configClazz);
    }

	public void setTestAndStatus(ConfigCode configCodeOld, ProjectCode test, ProjectMethodMember method) {
		this.setTestAndStatus(configCodeOld, test, method);
	}
    
    @Override
    public boolean equals(Object obj) {
        Margin.instance().inc();
        System.out.print(Margin.instance().tabs() + "Compruebo si el metodo " + this.name + " es igual a: " + ((ConfigMember) obj).getName() + " -->");
        if ((obj instanceof ConfigMethodMember)) {
            ConfigMethodMember configMethodMember = (ConfigMethodMember) obj;            
            if (!this.name.equals(configMethodMember.getName())) {
                System.out.println(" No lo es." + this.name + " != " + configMethodMember.getName());
                Margin.instance().dec();
                return false;
            }
            System.out.println(" Sí lo es.");
            Margin.instance().dec();
            return true;            
        } else {
            System.out.println(" No, es un método.");
            Margin.instance().dec();
            return false;
        }
    }

	@Override
	public void accept(ConfigWriter configWriter) {
		configWriter.visit(this);		
	}

	@Override
	public boolean isConfigConstructor() {
		return false;
	}

	@Override
	public boolean isConfigMethod() {
		return true;
	}
}
