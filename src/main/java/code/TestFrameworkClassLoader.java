package code;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestFrameworkClassLoader extends URLClassLoader{

    private static TestFrameworkClassLoader classLoader;
    
    private TestFrameworkClassLoader(URL[] path) {
       super(path); 
    }
    
    public static TestFrameworkClassLoader getClassLoader(){
        return classLoader;
    }
    
    public static void setClassLoaderByPath(String path) {
        try {
            URL baseUrl = new URL("file:///"+path);
            URL[] urls = {baseUrl};
            classLoader = new TestFrameworkClassLoader(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
}
