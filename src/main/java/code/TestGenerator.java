package code;

import writers.DataReaderWriter;
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
        
        TestWriter testWriter = new TestWriter(projectPath + "\\src\\test\\java\\");
        HeaderDataWriter headerDataWriter = new HeaderDataWriter(projectPath + "\\src\\test\\resources\\");
        DataReaderWriter dataReaderWriter = new DataReaderWriter(projectPath + "\\src\\test\\java\\", projectPath + "\\src\\test\\resources\\");
        
        main.accept(testWriter);
        main.accept(headerDataWriter);
//        main.accept(dataReaderWriter);
        
        testWriter.close();
        headerDataWriter.close();
//        dataReaderWriter.close();
//
//        ConfigCode configCode = new ConfigCode();
//        ConfigWriter configWriter = new ConfigWriter("C:\\Users\\nyuron\\Desktop", main);
        //main.accept(configWriter);


    }

    public static void main(String[] args) {
        new TestGenerator(args[0]);
    }
}
