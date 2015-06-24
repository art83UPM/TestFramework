package code;

import writers.DataReaderWriter;
import writers.HeaderDataWriter;
import writers.TestWriter;
import code.config.ConfigCode;
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
        //ProjectCode test = new ProjectCode(paths[1]);
        
        TestWriter testWriter = new TestWriter(projectPath + "\\src\\test\\java\\");
        HeaderDataWriter headerDataWriter = new HeaderDataWriter(projectPath + "\\src\\test\\resources\\");
        DataReaderWriter dataReaderWriter = new DataReaderWriter(projectPath + "\\src\\test\\java\\", projectPath + "\\src\\test\\resources\\");
        
        main.accept(testWriter);
        main.accept(headerDataWriter);
        main.accept(dataReaderWriter);

//        ConfigCode configCode = new ConfigCode(projectPath + "\\testConfig\\", main, test);
        //TODO cruzar datos de main y test  
        //ConfigWriter configWriter = new ConfigWriter("C:\\Users\\nyuron\\Desktop", main);

    }

    public static void main(String[] args) {
        new TestGenerator(args[0]);
    }
}
