package code;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class TestFrameworkClassLoader extends URLClassLoader{

    private static TestFrameworkClassLoader classLoader;
    
    private TestFrameworkClassLoader(URL[] path) {
       super(path); 
    }
    
    public static TestFrameworkClassLoader getClassLoader(){
        return classLoader;
    }
    
    public static void setClassLoaderByPath(String paths[]) {
        try {
        	URL[] urls = new URL[paths.length];
        	for (int i = 0; i < paths.length; i++) {
        		urls[i] = new URL("file:///"+paths[i]);
			}
            classLoader = new TestFrameworkClassLoader(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public static void setClassLoaderByPath(String path) {
    	String[] paths = {path};
    	setClassLoaderByPath(paths);
    }
}
