package spike;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonExample {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        
        obj.put("un string", "foo");
        obj.put("un entero", new Integer(100));
        obj.put("un double", new Double(1000.21));
        obj.put("un boolean", new Boolean(true));

        JSONArray unArray = new JSONArray();
        unArray.add("elemento 1");
        unArray.add("elemento 2");
        unArray.add("elemento 3");
        obj.put("Lista de elementos", unArray);

        File file = new File("C:\\Users\\nyuron\\Desktop\\jsonExample.json");
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(obj.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JSONParser parser = new JSONParser();
        
        try {
 
            JSONObject jsonLeido = (JSONObject) parser.parse(new FileReader(file));
 
            String elString = (String) jsonLeido.get("un string");
            int elEntero = (int) (long) jsonLeido.get("un entero");
            double elDouble = (double) jsonLeido.get("un double");
            boolean elBoolean = (boolean) jsonLeido.get("un boolean");
            JSONArray laLista = (JSONArray) jsonLeido.get("Lista de elementos");
 
            System.out.println("El string: " + elString);
            System.out.println("El entero: " + elEntero);
            System.out.println("El double: " + elDouble);
            System.out.println("El boolean: " + elBoolean);
            System.out.println("\nLista de elementost:");
            Iterator<String> iterator = laLista.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
