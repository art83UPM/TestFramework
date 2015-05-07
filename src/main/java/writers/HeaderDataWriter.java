package writers;

import code.Clazz;
import code.ConstructorMember;
import code.MethodMember;
import code.Package;
import code.ParameterMember;
import code.Visitor;

public class HeaderDataWriter implements Visitor {

    private DataWriter dataWriter;

    private String path;

    public HeaderDataWriter(String path) {
        this.path = path;
    }

    public void visit(Clazz clazz) {
        this.dataWriter = new DataWriter(path + "\\" + clazz.getName() + "TestData.xlsx");
    }

    public void visit(ConstructorMember constructorMember) {
        String header = "get" + constructorMember.getName();
        if (constructorMember.getParametersType().size() == 0) {
            dataWriter.write(header);
        } else {
            for (ParameterMember parameterMember : constructorMember.getParametersType()) {
                header += parameterMember.getType();
            }
            for (int i = 0; i < constructorMember.getParametersType().size(); i++) {
                dataWriter.write(header + "Parameter" + i);
            }
        }

    }

    public void visit(MethodMember methodMember) {
        String header = "get" + methodMember.getName();
        for (ParameterMember parameterMember : methodMember.getParametersType()) {
            header += parameterMember.getType();
        }
        for (int i = 0; i < methodMember.getParametersType().size(); i++) {
            dataWriter.write(header + "Parameter" + i);
        }
        dataWriter.write(header + "Result");
    }
    
    @Override
    public void visit(Package package1) {
        // TODO Auto-generated method stub
        
    }

    public void close() {
        dataWriter.save();
    }


}
