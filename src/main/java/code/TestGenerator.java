package code;

import writers.ConfigWriter;
import writers.HeaderDataWriter;
import writers.TestWriter;
import code.config.ConfigCode;
import code.project.ProjectCode;

public class TestGenerator {

    public TestGenerator(String projectPath) {
        TestFrameworkClassLoader.setClassLoaderByPath(projectPath + "\\target\\classes\\");
        ProjectCode main = new ProjectCode(projectPath + "\\target\\classes");
        TestFrameworkClassLoader.setClassLoaderByPath(projectPath + "\\target\\test-classes\\");
        ProjectCode test = new ProjectCode(projectPath + "\\target\\test-classes");
        
        TestWriter testWriter = new TestWriter("C:\\Users\\nyuron\\Desktop");
        HeaderDataWriter headerDataWriter = new HeaderDataWriter("C:\\Users\\nyuron\\Desktop");
        
//        clazz.accept(testWriter);
//        clazz.accept(headerDataWriter);
        
        testWriter.close();
        headerDataWriter.close();

        ConfigCode configCode = new ConfigCode();
        ConfigWriter configWriter = new ConfigWriter("C:\\Users\\nyuron\\Desktop", main);
        //main.accept(configWriter);

    }

    public static void main(String[] args) {
        new TestGenerator(args[0]);
    }
}
