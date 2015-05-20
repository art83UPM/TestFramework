package code.config;

import org.json.simple.JSONObject;

public class ConfigConstructorMember extends ConfigMember {

    public ConfigConstructorMember(JSONObject jsonConstructor) {
        super(jsonConstructor);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof ConfigConstructorMember)) {
            ConfigConstructorMember configConstructorMember = (ConfigConstructorMember) obj;
            if(!this.name.equals(configConstructorMember.getName())){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
