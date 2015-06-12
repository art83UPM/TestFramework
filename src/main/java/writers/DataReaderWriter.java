package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import readers.TestDataReader;
import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.Visitor;

public class DataReaderWriter implements Visitor {
	
	private File file;

    private String path;
    private String resourcesPath;

    private BufferedWriter writer;

    public DataReaderWriter(String path, String resourcesPath) {
        this.path = path;
        this.resourcesPath = resourcesPath;
    }

    private String packagePath(ProjectClazz clazz) {
    	return clazz.getProjectPackage().getName().replace(".", File.separator);
	}
    
    private String printTabs(int numTabs) {
        String result = "";
        for (int i = 0; i < numTabs; i++) {
            result = result + "   ";
        }
        return result;
    }

	@Override
	public void visit(ProjectClazz clazz) {
		this.file = new File(path + packagePath(clazz) + File.separator + "_dataReaders" + File.separator + clazz.getName() + "Test.java");
		try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("package " + clazz.getProjectPackage().getName() + "._dataReaders;\n\n");
            writer.write("import readers.TestDataReader;\n\n");
            writer.write("import readers.exceptions.EmptyDataReaderException;\n");
            writer.write("import readers.exceptions.InvalidDataReaderException;\n");
            writer.write("import spike.Example;\n\n");
            writer.write("public class " + clazz.getName() + "TestDataReader extends TestDataReader {\n\n");
            writer.write(this.printTabs(1) + "private " + clazz.getName() + " " + clazz.getName().toLowerCase() + ";\n\n");
            writer.write(this.printTabs(1) + "private final static int CONSTRUCTOR_QUANTITY = " + clazz.getConstructorMemberList().size() + ";\n\n");
            
            writer.write(this.printTabs(1) + "public "+ clazz.getName() + "TestDataReader() {\n");
            writer.write(this.printTabs(2) + "super("+ this.resourcesPath + clazz.getName() + "TestData.xlsx" +");\n");
            writer.write(this.printTabs(1) + "}\n\n");
            
            writer.write(this.printTabs(1) + "public boolean hasNext(int constructMode) {\n");
            writer.write(this.printTabs(2) + "while (this.hasNext()) {\n");
            writer.write(this.printTabs(3) + "this.getDataReader().next();\n");
            writer.write(this.printTabs(3) + "if (this.existsConstructor(constructMode)) {\n");
            writer.write(this.printTabs(4) + "return true;\n");
            writer.write(this.printTabs(3) + "}\n");
            writer.write(this.printTabs(2) + "}\n");
            writer.write(this.printTabs(2) + "return false;\n");
            writer.write(this.printTabs(1) + "}\n\n");

            writer.write(this.printTabs(1) + "public void next() {\n");
            writer.write(this.printTabs(2) + "this.getDataReader().next();\n");
            writer.write(this.printTabs(2) + "int i = 0;\n");
            writer.write(this.printTabs(2) + "this.example = null;\n");
            writer.write(this.printTabs(2) + "while (i < CONSTRUCTOR_QUANTITY && !this.existsConstructor(i)) {\n");
            writer.write(this.printTabs(3) + "i++;\n");
            writer.write(this.printTabs(2) + "}\n");
            writer.write(this.printTabs(2) + "this.construct(i);\n");
            writer.write(this.printTabs(1) + "}\n\n");
            
            writer.write(this.printTabs(1) + "public void next(int constructMode) {\n");
            writer.write(this.printTabs(2) + "this.example = null;\n");
            writer.write(this.printTabs(2) + "this.construct(constructMode);\n");
            writer.write(this.printTabs(2) + "this.example = null;\n");
            writer.write(this.printTabs(1) + "}\n\n");
            
            writer.write(this.printTabs(1) + "private boolean existsConstructor(int constructMode) {\n");
            writer.write(this.printTabs(2) + "boolean exists = true;\n");
            writer.write(this.printTabs(2) + "switch (constructMode) {\n");
            *** 
            writer.write(this.printTabs(1) + "}\n\n");
           
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

	@Override
	public void visit(ProjectConstructorMember constructorMember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ProjectMethodMember methodMember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ProjectPackage package1) {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

}
