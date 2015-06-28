package code;

import writers.ConfigWriter;
import writers.DataReaderWriter;
import writers.ExistentMarker;
import writers.GenerateMarker;
import writers.HeaderDataWriter;
import writers.TestWriter;
import code.project.ProjectCode;

public class TestGenerator {

    public TestGenerator(String projectPath) {
    	String paths[] = {
    			projectPath + "\\target\\classes\\",
    			projectPath + "\\target\\test-classes\\"
    	};
        TestFrameworkClassLoader.setClassLoaderByPath(paths[0]);
        ProjectCode main = new ProjectCode(paths[0]);
        TestFrameworkClassLoader.setClassLoaderByPath(paths);
        ProjectCode test = new ProjectCode(paths[1]);
        
        ConfigWriter configWriter = new ConfigWriter(projectPath + "\\testConfig\\", main, test);
        TestWriter testWriter = new TestWriter(projectPath + "\\src\\test\\java\\");
        HeaderDataWriter headerDataWriter = new HeaderDataWriter(projectPath + "\\src\\test\\resources\\");
        DataReaderWriter dataReaderWriter = new DataReaderWriter(projectPath + "\\src\\test\\java\\", projectPath + "\\src\\test\\resources\\");
        ExistentMarker existentMarker = new ExistentMarker();
        GenerateMarker generateMarker = new GenerateMarker();
        
        main.accept(testWriter);
        main.accept(headerDataWriter);
        main.accept(dataReaderWriter);
        main.accept(existentMarker);
        main.accept(generateMarker);

        configWriter.writeNewConfigCode();

    }

    public static void main(String[] args) {
        new TestGenerator(args[0]);
    }
}
