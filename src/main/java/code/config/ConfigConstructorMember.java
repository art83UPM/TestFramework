package code.config;

import org.json.simple.JSONObject;

import code.Margin;
import code.project.ProjectCode;
import code.project.ProjectConstructorMember;

public class ConfigConstructorMember extends ConfigMember {

    public ConfigConstructorMember(JSONObject jsonConstructor, ConfigClazz configClazz) {
        super(jsonConstructor, configClazz);
    }

    public ConfigConstructorMember(String name, ConfigClazz configClazz) {
        super(name, configClazz);
    }

	public void setTestAndStatus(ConfigCode configCodeOld, ProjectCode test, ProjectConstructorMember constructor) {
		super.setTestAndStatus(configCodeOld, test, constructor);		
	}

    @Override
    public boolean equals(Object obj) {
        Margin.instance().inc();
        System.out.print(Margin.instance().tabs() + "Compruebo si el metodo " + this.name + " es igual a: " + ((ConfigMember) obj).getName() + " -->");
        if ((obj instanceof ConfigConstructorMember)) {
            ConfigConstructorMember configConstructorMember = (ConfigConstructorMember) obj;            
            if(!this.name.equals(configConstructorMember.getName())){
                System.out.println(" No lo es.");
                Margin.instance().dec();
                return false;
            }
            System.out.println(" Sí lo es.");
            Margin.instance().dec();
            return true;
        } else {
            System.out.println(" No, es un constructor.");
            Margin.instance().dec();
            return false;
        }
    }
	
	public boolean isConfigConstructor() {
		return true;
	}

	@Override
	public boolean isConfigMethod() {
		return false;
	}
}
