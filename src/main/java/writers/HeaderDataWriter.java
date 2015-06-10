package writers;

import code.project.ProjectClazz;
import code.project.ProjectConstructorMember;
import code.project.ProjectMethodMember;
import code.project.ProjectPackage;
import code.project.ProjectParameterMember;
import code.project.Visitor;

public class HeaderDataWriter implements Visitor {

    private DataWriter dataWriter;

    private String path;

    public HeaderDataWriter(String path) {
        this.path = path;
    }

    public void visit(ProjectClazz clazz) {
        this.dataWriter = new DataWriter(path + "\\" + clazz.getName() + "TestData.xlsx");
    }

    public void visit(ProjectConstructorMember constructorMember) {
        dataWriter.setSheet("Constructors");
        String header = "get" + constructorMember.getName();
        if (constructorMember.getParameterNumber() == 0) {
            dataWriter.write(header);
        } else {
            for (ProjectParameterMember parameterMember : constructorMember.getParametersType()) {
                header += parameterMember.getType();
            }
            for (int i = 0; i < constructorMember.getParameterNumber(); i++) {
                dataWriter.write(header + "Parameter" + i);
            }
        }

    }

    public void visit(ProjectMethodMember methodMember) {
        String header = "get" + methodMember.getName();
        for (ProjectParameterMember parameterMember : methodMember.getParametersType()) {
            header += parameterMember.getType();
        }
        dataWriter.setSheet(header);
        for (int i = 0; i < methodMember.getParametersType().size(); i++) {
            dataWriter.write(header + "Parameter" + i);
        }
        dataWriter.write(header + "Result");
    }
    
    @Override
    public void visit(ProjectPackage package1) {        
    }

    public void close() {
        dataWriter.save();
    }


}
