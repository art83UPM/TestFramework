package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import util.Capitalizer;
import code.config.ConfigStatus;
import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.ProjectParameterMember;

public class DataReaderWriter implements ProjectVisitor {

    private static Map<String, String> initializations;

    private static final String[][] mapData = { {"int", "int result = 0;"}, {"String", "String result = null;"},
            {"float", "float result = 0;"}, {"double", "double result = 0;"}, {"boolean", "boolean result = false;"}};

    private File file;

    private String path;

    private String resourcesPath;

    private BufferedWriter writer;

    public DataReaderWriter(String path, String resourcesPath) {
        this.path = path;
        this.resourcesPath = resourcesPath;
        initializations = new HashMap<String, String>();
        for (String[] initialization : mapData) {
            initializations.put(initialization[0], initialization[1]);
        }
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
        if (clazz.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            this.file = new File(path + clazz.getPackagePath() + File.separator + "_dataReaders" + File.separator + clazz.getName()
                    + "TestDataReader.java");
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write("package " + clazz.getProjectPackage().getName() + "._dataReaders;\n\n");
                writer.write("import readers.TestDataReader;\n");
                writer.write("import readers.exceptions.EmptyDataReaderException;\n");
                writer.write("import readers.exceptions.InvalidDataReaderException;\n");
                writer.write("import " + clazz.getProjectPackage().getName() + "." + clazz.getName() + ";\n\n");
                writer.write("public class " + clazz.getName() + "TestDataReader extends TestDataReader {\n\n");
                writer.write(this.printTabs(1) + "private " + clazz.getName() + " " + clazz.getName().toLowerCase() + ";\n\n");
                writer.write(this.printTabs(1) + "private final static String[] CONSTRUCTOR_NAMES = {");
                int i = 0;
                for (ProjectConstructorMember constructor : clazz.getConstructorMemberList()) {
                    writer.write("\"" + constructor.getNameWithParams() + "\"");
                    if (i++ < clazz.getConstructorMemberList().size() - 1) {
                        writer.write(", ");
                    }
                }
                writer.write("};\n\n");

                writer.write(this.printTabs(1) + "public " + clazz.getName() + "TestDataReader() {\n");
                String dataSheetPath = this.resourcesPath + clazz.getPackagePath() + File.separator + clazz.getName() + "TestData.xlsx";
                dataSheetPath = dataSheetPath.replace("\\", "\\\\");
                writer.write(this.printTabs(2) + "super(\"" + dataSheetPath + "\");\n");
                writer.write(this.printTabs(1) + "}\n\n");

                writer.write(this.printTabs(1) + "public boolean hasNext(String constructorName) {\n");
                writer.write(this.printTabs(2) + "while (this.hasNext()) {\n");
                writer.write(this.printTabs(3) + "this.getDataReader().next();\n");
                writer.write(this.printTabs(3) + "if (this.existsConstructor(constructorName)) {\n");
                writer.write(this.printTabs(4) + "return true;\n");
                writer.write(this.printTabs(3) + "}\n");
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(2) + "return false;\n");
                writer.write(this.printTabs(1) + "}\n\n");

                writer.write(this.printTabs(1) + "public void next() {\n");
                writer.write(this.printTabs(2) + "this.getDataReader().next();\n");
                writer.write(this.printTabs(2) + "int i = 0;\n");
                writer.write(this.printTabs(2) + "this." + Capitalizer.unCapitalize(clazz.getName()) + "= null;\n");
                writer.write(this.printTabs(2)
                        + "while (i < CONSTRUCTOR_NAMES.length && !this.existsConstructor(CONSTRUCTOR_NAMES[i])) {\n");
                writer.write(this.printTabs(3) + "i++;\n");
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(2) + "this.construct(CONSTRUCTOR_NAMES[i]);\n");
                writer.write(this.printTabs(1) + "}\n\n");

                writer.write(this.printTabs(1) + "public void next(String constructorName) {\n");
                writer.write(this.printTabs(2) + "this." + Capitalizer.unCapitalize(clazz.getName()) + "= null;\n");
                writer.write(this.printTabs(2) + "this.construct(constructorName);\n");
                writer.write(this.printTabs(1) + "}\n\n");

                writer.write(this.printTabs(1) + "private boolean existsConstructor(String constructorName) {\n");
                writer.write(this.printTabs(2) + "switch (constructorName) {\n");
                for (ProjectConstructorMember constructor : clazz.getConstructorMemberList()) {
                    writer.write(this.printTabs(2) + "case \"" + constructor.getNameWithParams() + "\":\n");
                    writer.write(this.printTabs(3) + "return existsConstructor" + constructor.getNameWithParams() + "();\n\n");
                }
                writer.write(this.printTabs(2) + "default:\n");
                writer.write(this.printTabs(3) + "return false;\n");
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(1) + "}\n\n");

                writer.write(this.printTabs(1) + "private void construct(String constructorName) {\n");
                writer.write(this.printTabs(2) + "switch (constructorName) {\n");
                for (ProjectConstructorMember constructor : clazz.getConstructorMemberList()) {
                    writer.write(this.printTabs(2) + "case \"" + constructor.getNameWithParams() + "\":\n");
                    writer.write(this.printTabs(3) + "construct" + constructor.getNameWithParams() + "();\n");
                    writer.write(this.printTabs(3) + "break;\n\n");
                }
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(1) + "}\n\n");

                writer.write(this.printTabs(1) + "public " + clazz.getName() + " get" + clazz.getName() + "() {\n");
                writer.write(this.printTabs(2) + "return this." + clazz.getName().toLowerCase() + ";\n");
                writer.write(this.printTabs(1) + "}\n\n");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void visit(ProjectConstructorMember constructorMember) {
        if (constructorMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            try {
                writer.write(this.printTabs(1) + "private boolean existsConstructor" + constructorMember.getNameWithParams() + "() {\n");
                writer.write(this.printTabs(2) + "try {\n");
                if (constructorMember.getParameterNumber() == 0) {
                    writer.write(this.printTabs(3) + "String x = this.getString(\"get" + constructorMember.getNameWithParams() + "\");\n");
                    writer.write(this.printTabs(3) + "if (!x.equalsIgnoreCase(\"x\")) {\n");
                    writer.write(this.printTabs(4) + "throw new InvalidDataReaderException(\"Data under column \\\"get"
                            + constructorMember.getNameWithParams()
                            + "\\\" at row: \"+ this.getDataReader().getRow()+ \" should be x or X\");\n");
                    writer.write(this.printTabs(3) + "}\n");
                    writer.write(this.printTabs(2) + "} catch (InvalidDataReaderException e) {\n");
                    writer.write(this.printTabs(3) + "System.out.println(e.getMessage());\n");
                    writer.write(this.printTabs(3) + "System.exit(0);\n");
                } else {
                    for (int i = 0; i < constructorMember.getParameterNumber(); i++) {
                        writer.write(this.printTabs(3) + "this.getInt(\"get" + constructorMember.getNameWithParams() + "Parameter" + i
                                + "\");\n");
                    }
                }
                writer.write(this.printTabs(2) + "} catch (EmptyDataReaderException e) {\n");
                writer.write(this.printTabs(3) + "return false;\n");
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(2) + "return true;\n");
                writer.write(this.printTabs(1) + "}\n\n");

                writer.write(this.printTabs(1) + "private void construct" + constructorMember.getNameWithParams() + "() {\n");
                if (constructorMember.getParameterNumber() == 0) {
                    writer.write(this.printTabs(2) + "this." + Capitalizer.unCapitalize(constructorMember.getName()) + "= new "
                            + constructorMember.getName() + "();\n");
                } else {
                    writer.write(this.printTabs(2) + "try {\n");
                    for (int j = 0; j < constructorMember.getParametersType().size(); j++) {
                        ProjectParameterMember parameter = constructorMember.getParametersType().get(j);
                        writer.write(this.printTabs(3) + parameter.getType() + " " + constructorMember.getNameWithParams() + "Parameter"
                                + j + " = this.get" + Capitalizer.capitalize(parameter.getType()) + "(\"get"
                                + constructorMember.getNameWithParams() + "Parameter" + j + "\");\n");
                    }
                    writer.write(this.printTabs(2) + "this." + Capitalizer.unCapitalize(constructorMember.getName()) + "= new "
                            + constructorMember.getName() + "(");
                    for (int k = 0; k < constructorMember.getParametersType().size(); k++) {
                        writer.write(constructorMember.getNameWithParams() + "Parameter" + k);
                        if (k < constructorMember.getParametersType().size() - 1) {
                            writer.write(", ");
                        }
                    }
                    writer.write(");\n");
                    writer.write(this.printTabs(2) + "} catch (EmptyDataReaderException e) {}\n");
                }
                writer.write(this.printTabs(1) + "}\n\n");

            } catch (IOException | NullPointerException e) {
                System.out.println("Can't generate constructor " + constructorMember.getNameWithParams() + " because class " + constructorMember.getProjectClazz().getName() + " is not marked as GENERATE.");
            }
        }
    }

    @Override
    public void visit(ProjectMethodMember methodMember) {
        if (methodMember.getConfigMember().getStatus() == ConfigStatus.GENERATE) {
            try {
                writer.write(this.printTabs(1) + "public " + methodMember.getReturnType() + " get"
                        + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Result() {\n");
                writer.write(this.printTabs(2) + "this.setTestTarget(\"test" + Capitalizer.capitalize(methodMember.getNameWithParams())
                        + "\");\n");
                writer.write(this.printTabs(2) + "this.getDataReader().next();\n");
                writer.write(this.printTabs(2) + initializations.get(methodMember.getReturnType()) + "\n");
                writer.write(this.printTabs(2) + "try {\n");
                writer.write(this.printTabs(3) + "result = this.get" + Capitalizer.capitalize(methodMember.getReturnType()) + "(\"get"
                        + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Result\");\n");
                writer.write(this.printTabs(2) + "} catch (EmptyDataReaderException e) {\n");
                writer.write(this.printTabs(3) + "System.out.println(\"Error in " + "get"
                        + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Result\");\n");
                writer.write(this.printTabs(3) + "System.out.println(e.getMessage());\n");
                writer.write(this.printTabs(3) + "System.exit(0);\n");
                writer.write(this.printTabs(2) + "}\n");
                writer.write(this.printTabs(2) + "return result;\n");
                writer.write(this.printTabs(1) + "}\n\n");

                for (int i = 0; i < methodMember.getParametersType().size(); i++) {
                    ProjectParameterMember parameter = methodMember.getParametersType().get(i);
                    writer.write(this.printTabs(1) + "public " + parameter.getType() + " get"
                            + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Parameter" + i + "() {\n");
                    writer.write(this.printTabs(2) + "this.setTestTarget(\"test" + Capitalizer.capitalize(methodMember.getNameWithParams())
                            + "\");\n");
                    writer.write(this.printTabs(2) + initializations.get(parameter.getType()) + "\n");
                    writer.write(this.printTabs(2) + "try {\n");
                    writer.write(this.printTabs(3) + "result = this.get" + Capitalizer.capitalize(parameter.getType()) + "(\"get"
                            + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Parameter" + i + "\");\n");
                    writer.write(this.printTabs(2) + "} catch (EmptyDataReaderException e) {\n");
                    writer.write(this.printTabs(3) + "System.out.println(\"Error in " + "get"
                            + Capitalizer.capitalize(methodMember.getNameWithParams()) + "Parameter" + i + "\");\n");
                    writer.write(this.printTabs(3) + "System.out.println(e.getMessage());\n");
                    writer.write(this.printTabs(3) + "System.exit(0);\n");
                    writer.write(this.printTabs(2) + "}\n");
                    writer.write(this.printTabs(2) + "return result;\n");
                    writer.write(this.printTabs(1) + "}\n\n");
                }

            } catch (IOException | NullPointerException e) {
                System.out.println("Can't generate method " + methodMember.getNameWithParams() + " because class " + methodMember.getProjectClazz().getName() + " is not marked as GENERATE.");
            }
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

}
