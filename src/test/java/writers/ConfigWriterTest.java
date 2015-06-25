package writers;

import code.TestFrameworkClassLoader;
import code.project.ProjectCode;

public class ConfigWriterTest {

    public static void main(String[] args) {
        String projectPath = "C:\\Users\\CarlosDavid\\git\\exampleProject";
        String paths[] = {
                projectPath + "\\target\\classes\\",
                projectPath + "\\target\\test-classes\\"
        };
        TestFrameworkClassLoader.setClassLoaderByPath(paths[0]);
        ProjectCode main = new ProjectCode(paths[0]);
        TestFrameworkClassLoader.setClassLoaderByPath(paths);
        ProjectCode test = new ProjectCode(paths[1]);
        
        ConfigWriter cfgWriter = new ConfigWriter(projectPath + "\\testConfig\\", main, test);
        cfgWriter.writeNewConfigCode();
    }

}
