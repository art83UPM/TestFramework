package code;

import writers.HeaderDataWriter;
import writers.TestWriter;
import code.project.ProjectCode;

public class TestGenerator {

    public TestGenerator(String projectPath) {
        ProjectCode main = new ProjectCode(projectPath + "\\target\\classes\\");
        ProjectCode test = new ProjectCode(projectPath + "\\target\\test-classes\\");
        
        TestWriter testWriter = new TestWriter(projectPath + "\\src\\test\\java");
        HeaderDataWriter headerDataWriter = new HeaderDataWriter(projectPath + "\\src\\test\\resources");
        
        main.accept(testWriter);
//        main.accept(headerDataWriter);
        
        testWriter.close();
        headerDataWriter.close();
//
//        ConfigCode configCode = new ConfigCode();
//        ConfigWriter configWriter = new ConfigWriter("C:\\Users\\nyuron\\Desktop", main);
        //main.accept(configWriter);

    }

    public static void main(String[] args) {
        new TestGenerator(args[0]);
    }
}
