package code.config;

import org.json.simple.JSONObject;

public class ConfigMethodMember extends ConfigMember{
    
    public ConfigMethodMember(JSONObject jsonMethod) {
       super(jsonMethod);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof ConfigMethodMember)) {
            ConfigMethodMember configMethodMember = (ConfigMethodMember) obj;
            if(!this.name.equals(configMethodMember.getName())){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
    
}
