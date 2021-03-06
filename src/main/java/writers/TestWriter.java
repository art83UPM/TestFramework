package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import util.Capitalizer;
import code.config.ConfigStatus;
import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;

public class TestWriter implements ProjectVisitor {

    private File file;

    private String path;

    private BufferedWriter writer;

    public TestWriter(String path) {
        this.path = path;
    }

    public void visit(ProjectClazz clazz) {
        if (clazz.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            this.file = new File(path + clazz.getPackagePath() + File.separator + clazz.getName() + "Test.java");
            this.file.getParentFile().mkdirs();
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write("package " + clazz.getProjectPackage().getName() + ";\n\n");
                writer.write("import static org.junit.Assert.*;\n\n");
                writer.write("import org.junit.After;\n");
                writer.write("import org.junit.BeforeClass;\n");
                writer.write("import org.junit.Test;\n\n");
                writer.write("import " + clazz.getProjectPackage().getName() + "." + clazz.getName() + ";\n");
                writer.write("import " + clazz.getProjectPackage().getName() + "._dataReaders." + clazz.getName() + "TestDataReader;\n\n");
                writer.write("public class " + clazz.getName() + "Test {\n\n");
                writer.write(this.printTabs(1) + "private static " + clazz.getName() + "TestDataReader data;\n\n");
                writer.write(this.printTabs(1) + "@BeforeClass\n");
                writer.write(this.printTabs(1) + "public static void init() {\n");
                writer.write(this.printTabs(2) + "   data = new " + clazz.getName() + "TestDataReader();\n");
                writer.write(this.printTabs(1) + "}\n\n");
                writer.write(this.printTabs(1) + "@After\n");
                writer.write(this.printTabs(1) + "public void reset() {\n");
                writer.write(this.printTabs(2) + "data.reset();\n");
                writer.write(this.printTabs(1) + "}\n\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void visit(ProjectConstructorMember constructorMember) {
        if (constructorMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            try {
                writer.write(this.printTabs(1) + "@Test\n");
                writer.write(this.printTabs(1) + "public void test" + constructorMember.getNameWithParams() + "() {\n");
                writer.write(this.printTabs(2) + "while (data.hasNext(\"" + constructorMember.getNameWithParams() + "\")) {\n");
                writer.write(this.printTabs(3) + constructorMember.getName() + " " + constructorMember.getName().toLowerCase()
                        + " = data.get" + constructorMember.getName() + "();\n");
                writer.write(this.printTabs(3) + "fail(\"Not yet implemented\");\n");
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(1) + "}\n\n");
            } catch (IOException | NullPointerException e) {
                System.out.println("Can't generate constructor " + constructorMember.getNameWithParams() + " because class " + constructorMember.getProjectClazz().getName() + " is not marked as GENERATE.");
            }
            constructorMember.getConfigMember().setTest("test" + constructorMember.getNameWithParams());
        }
    }

    public void visit(ProjectMethodMember methodMember) {
        if (methodMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            try {
                writer.write(this.printTabs(1) + "@Test\n");
                writer.write(this.printTabs(1) + "public void test" + Capitalizer.capitalize(methodMember.getNameWithParams()));
                writer.write("() {\n");
                writer.write(this.printTabs(2) + "while (data.hasNext()) {\n");
                writer.write(this.printTabs(3) + "data.next();\n");
                writer.write(this.printTabs(3) + "assertEquals(data.get" + Capitalizer.capitalize(methodMember.getNameWithParams())
                        + "Result(), data.get" + methodMember.getProjectClazzName() + "()."
                        + methodMember.getName().substring(0, 1).toLowerCase() + methodMember.getName().substring(1) + "(");
                for (int i = 0; i < methodMember.getParametersType().size() - 1; i++) {
                    writer.write("data.get" + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Parameter" + i + "(),");
                }
                if (methodMember.getParametersType().size() > 0) {
                    writer.write("data.get" + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Parameter"
                            + (methodMember.getParametersType().size() - 1) + "()");
                }
                writer.write("));\n");
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(1) + "}\n\n");
            } catch (IOException | NullPointerException e) {
                System.out.println("Can't generate method " + methodMember.getNameWithParams() + " because class " + methodMember.getProjectClazz().getName() + " is not marked as GENERATE.");
            }
            methodMember.getConfigMember().setTest("test" + methodMember.getNameWithParams());
        }
    }

    @Override
    public void visit(ProjectPackage package1) {
    }

    public void close() {
        if (writer != null) {
            try {
                writer.write("}\n\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String printTabs(int numTabs) {
        String result = "";
        for (int i = 0; i < numTabs; i++) {
            result = result + "   ";
        }
        return result;
    }

}
