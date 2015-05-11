package code;

import code.project.ProjectClazz;
import code.project.ProjectCode;
import readers.ClazzReader;
import writers.HeaderDataWriter;
import writers.TestWriter;

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
    }

    public void classReaderTester(String clazzName) {
        try {
            ClazzReader clazzReader = new ClazzReader(Class.forName(clazzName));
            ProjectClazz clazz = clazzReader.read();
            TestWriter testWriter = new TestWriter("C:\\Users\\nyuron\\Desktop");
            HeaderDataWriter headerDataWriter = new HeaderDataWriter("C:\\Users\\nyuron\\Desktop");
            clazz.accept(testWriter);
            clazz.accept(headerDataWriter);
            testWriter.close();
            headerDataWriter.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new TestGenerator(args[0]);
    }
}
