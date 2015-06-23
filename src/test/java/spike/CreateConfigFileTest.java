package spike;

import writers.ConfigWriter;
import code.TestFrameworkClassLoader;
import code.project.ProjectCode;

public class CreateConfigFileTest {

	protected CreateConfigFileTest(String projectPath) {
		String paths[] = {
    			projectPath + "\\target\\classes\\",
    			projectPath + "\\target\\test-classes\\"
    	};
        TestFrameworkClassLoader.setClassLoaderByPath(paths[0]);
        ProjectCode main = new ProjectCode(paths[0]);
        TestFrameworkClassLoader.setClassLoaderByPath(paths);
        ProjectCode test = new ProjectCode(paths[1]);
        
        ConfigWriter configWriter = new ConfigWriter("C:\\Users\\Irene\\Desktop", main, test);
        configWriter.writeNewConfigCode();
	}
	
	public static void main(String[] args) {
		new CreateConfigFileTest(args[0]);
	}
}
