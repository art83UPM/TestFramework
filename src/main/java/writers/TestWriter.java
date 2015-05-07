package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import code.Clazz;
import code.ConstructorMember;
import code.MethodMember;
import code.Package;
import code.ParameterMember;
import code.Visitor;

public class TestWriter implements Visitor {

    private File file;

    private String path;

    private BufferedWriter writer;

    public TestWriter(String path) {
        this.path = path;
    }

    public void visit(Clazz clazz) {
        this.file = new File(path + File.separator + clazz.getName() + "Test.java");
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("package " + clazz.getPackage() + "Test.generated;\n\n");
            writer.write("import static org.junit.Assert.*;\n\n");
            writer.write("import org.junit.After;\n");
            writer.write("import org.junit.BeforeClass;\n");
            writer.write("import org.junit.Test;\n\n");
            writer.write("import " + clazz.getPackage() + "." + clazz.getName() + ";\n");
            writer.write("import " + clazz.getPackage() + ".generated." + clazz.getName() + "TestDataReader;\n\n");
            writer.write("public class " + clazz.getName() + "Test {\n\n");
            writer.write(this.printTabs(1) + "private " + clazz.getName() + "TestDataReader data;\n\n");
            writer.write(this.printTabs(1) + "@BeforeClass\n");
            writer.write(this.printTabs(1) + "public void init() {\n");
            writer.write(this.printTabs(2) + "\tdata = new " + clazz.getName() + "TestDataReader();\n");
            writer.write(this.printTabs(1) + "}\n\n");
            writer.write(this.printTabs(1) + "@After\n");
            writer.write(this.printTabs(1) + "public void reset() {\n");
            writer.write(this.printTabs(1) + "}\n\n");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void visit(ConstructorMember constructorMember) {//TODO que los constructores esten ordenados
        try {
            writer.write(this.printTabs(1) + "@Test\n");
            writer.write(this.printTabs(1) + "public void test" + constructorMember.getName());
            for (ParameterMember parameterMember : constructorMember.getParametersType()) {
                writer.write(parameterMember.getType());
            }
            writer.write("() {\n");
            writer.write(this.printTabs(2) + "while (data.hasNext(" + constructorMember.getOrder() + ")) {\n");
            writer.write(this.printTabs(3) + "" + constructorMember.getName() + " " + constructorMember.getName().toLowerCase()
                    + " = data.get" + constructorMember.getName() + "();\n");
            writer.write(this.printTabs(3) + "fail(\"Not yet implemented\");\n");
            writer.write(this.printTabs(3) + "data.next();\n");
            writer.write(this.printTabs(2) + "}\n");
            writer.write(this.printTabs(1) + "}\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visit(MethodMember methodMember) {//TODO que los metodos esten ordenados
        String nameAndParametersType = methodMember.getName();
        for (ParameterMember parameterMember : methodMember.getParametersType()) {
            nameAndParametersType += parameterMember.getType();
        }
        try {
            writer.write(this.printTabs(1) + "@Test\n");
            writer.write(this.printTabs(1) + "public void test" + nameAndParametersType);
            writer.write("() {\n");
            writer.write(this.printTabs(2) + "while (data.hasNext()) {\n");
            writer.write(this.printTabs(3) + "assertEquals(data.get" + nameAndParametersType + "Result(), data.get"
                    + methodMember.getClazzName() + "()." + methodMember.getName().toLowerCase() + "(");
            for (int i = 0; i < methodMember.getParametersType().size()-1; i++) {
                writer.write("data.get"+nameAndParametersType+"Parameter"+i+"(),");
            }
            writer.write("data.get"+nameAndParametersType+"Parameter"+ (methodMember.getParametersType().size()-1)+"()));\n");
            writer.write(this.printTabs(3) + "data.next();\n");
            writer.write(this.printTabs(2) + "}\n");
            writer.write(this.printTabs(1) + "}\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void visit(Package package1) {
        // TODO Auto-generated method stub
        
    }
 
    public void close() {
        try {
            writer.write("}\n\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
