package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonHelper {

    private JsonHelper() {

    }

    public static JSONObject getJsonObjectFromArray(String name, JSONArray array) {
        if (array == null) {
            return null;
        } else {
            JSONObject result = null;
            int i = 0;
            while (result == null && i < array.size()) {
                if (((String) ((JSONObject) array.get(i)).get("name")).equals(name)) {
                    result = (JSONObject) array.get(i);
                }
                i++;
            }
            return result;
        }
    }

    public static boolean isEmpty(File file) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {
                br.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static JSONArray getJsonArray(JSONObject oldConfigCode, String string) {
        if (oldConfigCode == null) {
            return null;
        } else {
            return (JSONArray) oldConfigCode.get("packages");
        }
    }

    public static String getString(JSONObject oldConfigMember, String string) {
        if (oldConfigMember == null) {
            return null;
        } else {
            return (String) oldConfigMember.get("status");
        }
    }
}
