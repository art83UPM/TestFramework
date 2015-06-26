package util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonHelper {

    private JsonHelper() {
        
    }
    
    public static JSONObject getJsonObjectFromArray (String name, JSONArray array) {
        JSONObject result = null;
        int i = 0;
        while (result == null && i < array.size()) {
            if (((String)((JSONObject)array.get(i)).get("name")).equals(name)) {
                result = (JSONObject)array.get(i);
            }
        }
        return result;
    }
}
